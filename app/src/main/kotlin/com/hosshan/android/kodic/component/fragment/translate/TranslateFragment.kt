package com.hosshan.android.kodic.component.fragment.translate

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import butterknife.bindView
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.adapter.TranslatedTextAdapter
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.data.realm.TranslatedHistory
import com.hosshan.android.kodic.model.TranslatedText
import com.hosshan.android.kodic.store.codic.CodicRequestSubscriber
import com.hosshan.android.kodic.store.codic.EngineStore
import com.hosshan.android.kodic.store.realm.TranslatedStore
import com.hosshan.android.kodic.util.addComposite
import rx.Observable
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/14.
 */
public class TranslateFragment : BaseFragment() {

    companion object {
        val KEY_PROJECT_ID: String = "project_id"

        @JvmStatic public fun createArg(projectId: Int): Bundle {
            val args: Bundle = Bundle()
            args.putInt(KEY_PROJECT_ID, projectId)
            return args
        }

        @JvmStatic public fun newInstance(projectId: Int): TranslateFragment {
            val fragment: TranslateFragment = TranslateFragment()
            fragment.arguments = createArg(projectId)
            return fragment
        }
    }

    var projectId: Int by Delegates.notNull()
    val caseSpinner: Spinner by bindView(R.id.translate_spinner_case)
    val acronymSpinner: Spinner by bindView(R.id.translate_spinner_acronym)
    val editText: EditText by bindView(R.id.translate_edittext_text)
    val button: Button by bindView(R.id.translate_button)
    val resultRecyclerView: RecyclerView by bindView(R.id.translate_recyclerview_result)

    var adapter: TranslatedTextAdapter by Delegates.notNull()
    @Inject lateinit var engineStore: EngineStore
    @Inject lateinit var translatedStore: TranslatedStore

    private val cases: List<String> = arrayListOf(
            "none",
            "camel",
            "pascal",
            "lower underscore",
            "upper underscore",
            "hyphen"
    )

    private val acronym: List<String> = arrayListOf(
            "MS naming guidelines",
            "camel strict",
            "literal"
    )

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        TranslateComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: Bundle? = arguments
        args?.getInt(KEY_PROJECT_ID)?.let {
            projectId = it
        }
        adapter = TranslatedTextAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        caseSpinner.adapter = ArrayAdapter<String>(activity, R.layout.item_translate_spinner_item, cases)
        caseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    acronymSpinner.visibility = View.GONE
                } else {
                    acronymSpinner.visibility = View.VISIBLE
                }
            }
        }

        acronymSpinner.adapter = ArrayAdapter<String>(activity, R.layout.item_translate_spinner_item, acronym)
        acronymSpinner.visibility = View.GONE

        resultRecyclerView.layoutManager = LinearLayoutManager(activity)
        resultRecyclerView.adapter = adapter

        button.setOnClickListener {
            // Close Keyboard
            val inputMethodManager: InputMethodManager? = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(editText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val observableTranslatedText: Observable<List<TranslatedText>>
            if (caseSpinner.selectedItemPosition == 0) {
                observableTranslatedText = engineStore.getTranslate(editText.text.toString(), projectId)
            } else {
                observableTranslatedText = engineStore.getTranslate(editText.text.toString(), projectId, caseSpinner.selectedItem as String, acronymSpinner.selectedItem as String)
            }
            observableTranslatedText
                    .addComposite(compositeSubscription)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CodicRequestSubscriber<List<TranslatedText>>(this) {
                        override fun onCompleted() {
                            super.onCompleted()
                            editText.setText("")
                        }

                        override fun onNext(items: List<TranslatedText>?) {
                            super.onNext(items)
                            adapter.insertAll(0, items)
                            items?.forEach {
                                translatedStore.saveTranslatedHistory(projectId, it)
                            }
                        }
                    })
        }

        translatedStore.getTranslatedHistory(projectId).map {
            adapter.add(toTranslatedText(it))
        }
    }

    private fun toTranslatedText(translatedHistory: TranslatedHistory): TranslatedText =
            TranslatedText(translatedHistory.successful,
                    translatedHistory.text,
                    translatedHistory.translatedText,
                    ArrayList())

}
