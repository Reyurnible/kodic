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
import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.adapter.TranslatedTextAdapter
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.model.TranslatedText
import com.hosshan.android.kodic.store.codic.EngineStore
import retrofit.RetrofitError
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
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

    var projectId: Int? = null
    val caseSpinner: Spinner by bindView(R.id.translate_spinner_case)
    val acronymSpinner: Spinner by bindView(R.id.translate_spinner_acronym)
    val editText: EditText by bindView(R.id.translate_edittext_text)
    val button: Button by bindView(R.id.translate_button)
    val resultRecyclerView: RecyclerView by bindView(R.id.translate_recyclerview_result)

    var adapter: TranslatedTextAdapter by Delegates.notNull()
    @Inject lateinit var engineStore: EngineStore

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: Bundle? = arguments
        projectId = args?.getInt(KEY_PROJECT_ID)
        if (projectId == null) {
            activity?.finish()
        }
        TranslateComponent.Initializer.init(activity).inject(this)
        adapter = TranslatedTextAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        caseSpinner.adapter = ArrayAdapter<String>(getActivity(), R.layout.item_translate_spinner_item, cases)
        caseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    acronymSpinner.setVisibility(View.GONE)
                } else {
                    acronymSpinner.setVisibility(View.VISIBLE)
                }
            }
        }

        acronymSpinner.adapter = ArrayAdapter<String>(getActivity(), R.layout.item_translate_spinner_item, acronym)
        acronymSpinner.visibility = View.GONE

        button.setOnClickListener {
            // Close Keyboard
            val inputMethodManager: InputMethodManager? = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)

            val observableTranslatedText: Observable<List<TranslatedText>>
            if (caseSpinner.selectedItemPosition == 0) {
                observableTranslatedText = engineStore.getTranslate(editText.text.toString(), projectId!!)
            } else {
                observableTranslatedText = engineStore.getTranslate(editText.text.toString(), projectId!!, caseSpinner.selectedItem as String, acronymSpinner.selectedItem as String)
            }
            observableTranslatedText
                    .lift(OperatorAddToCompositeSubscription<List<TranslatedText>>(compositeSubscription))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<List<TranslatedText>>() {
                        override fun onError(e: Throwable?) {
                            if (e is RetrofitError) {
                                Toast.makeText(activity, e.response.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCompleted() {
                            editText.setText("")
                        }

                        override fun onNext(items: List<TranslatedText>?) {
                            adapter.insertAll(0, items)
                        }
                    })
        }

        resultRecyclerView.layoutManager = LinearLayoutManager(getActivity())
        resultRecyclerView.adapter = adapter
    }

}
