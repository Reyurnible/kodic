package com.hosshan.android.godicparents.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.model.TranslatedText
import com.hosshan.android.godicparents.store.adapter.TranslateStoreAdapter
import rx.Observable
import rx.Subscriber
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/14.
 */
public class TranslateFragment : BaseFragment() {

    companion object {
        val KEY_PROJECT_ID: String = "project_id"

        platformStatic public fun newInstance(projectId: Int): TranslateFragment {
            val fragment: TranslateFragment = TranslateFragment()
            val args: Bundle = Bundle()
            args.putInt(KEY_PROJECT_ID, projectId)
            fragment.setArguments(args)
            return fragment
        }
    }

    var projectId: Int? = null
    var caseSpinner: Spinner by Delegates.notNull()
    var acronymSpinner: Spinner by Delegates.notNull()
    var editText: EditText by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: Bundle? = getArguments()
        projectId = args?.getInt(KEY_PROJECT_ID)
        if (projectId == null) {
            getActivity()?.finish()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_translate, container, false)

        caseSpinner = rootView.findViewById(R.id.translate_spinner_case) as Spinner
        acronymSpinner = rootView.findViewById(R.id.translate_spinner_acronym) as Spinner
        editText = rootView.findViewById(R.id.translate_edittext_text) as EditText

        setCaseSpinner()

        setAcronymSpinner()

        rootView.findViewById(R.id.translate_button).setOnClickListener {
            val observableTranslatedText: Observable<List<TranslatedText>>
            if (caseSpinner.getSelectedItemPosition() == 0) {
                observableTranslatedText = TranslateStoreAdapter.getTranslate(getActivity(), editText.getText().toString(), projectId!!)
            } else {
                observableTranslatedText = TranslateStoreAdapter.getTranslate(getActivity(), editText.getText().toString(), projectId!!, caseSpinner.getSelectedItem() as String, acronymSpinner.getSelectedItem() as String)
            }
            observableTranslatedText
                    .lift(OperatorAddToCompositeSubscription<List<TranslatedText>>(compositeSubscription))
                    .subscribe(object : Subscriber<List<TranslatedText>>() {
                        override fun onError(e: Throwable?) {

                        }

                        override fun onCompleted() {

                        }

                        override fun onNext(items: List<TranslatedText>?) {
                            // TODO リストの表示処理
                        }
                    })
        }

        return rootView
    }

    private fun setCaseSpinner() {
        val cases: List<String> = arrayListOf(
                "none",
                "camel",
                "pascal",
                "lower underscore",
                "upper underscore",
                "hyphen"
        )
        val caseAdapter: ArrayAdapter<String> = ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, cases)
        caseSpinner.setAdapter(caseAdapter)
        caseSpinner.setOnItemClickListener { adapterView, view, index, l ->
            if (index == 0) {
                acronymSpinner.setVisibility(View.GONE)
            } else {
                acronymSpinner.setVisibility(View.VISIBLE)
            }
        }
    }

    private fun setAcronymSpinner() {
        val acronym: List<String> = arrayListOf(
                "MS naming guidelines",
                "camel strict",
                "literal"
        )
        val acronymAdapter: ArrayAdapter<String> = ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, acronym)
        acronymSpinner.setAdapter(acronymAdapter)
        acronymSpinner.setVisibility(View.GONE)
    }


}