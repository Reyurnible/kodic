package com.hosshan.android.godicparents.component.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.hosshan.android.godicparents.R
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/14.
 */
public class TranslateFragment : Fragment() {

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_translate, container, false)

        caseSpinner = rootView.findViewById(R.id.translate_spinner_case) as Spinner
        acronymSpinner = rootView.findViewById(R.id.translate_spinner_acronym) as Spinner
        editText = rootView.findViewById(R.id.translate_edittext_text) as EditText

        setCaseSpinner()

        setAcronymSpinner()

        rootView.findViewById(R.id.translate_button).setOnClickListener {

        }

        return rootView
    }

    private fun setCaseSpinner() {
        val cases: List<String> = arrayListOf(
                "camel",
                "pascal",
                "lower underscore",
                "upper underscore",
                "hyphen"
        )
        val caseAdapter: ArrayAdapter<String> = ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, cases)
        caseSpinner.setAdapter(caseAdapter)
    }

    private fun setAcronymSpinner() {
        val acronym: List<String> = arrayListOf(
                "MS naming guidelines",
                "camel strict",
                "literal"
        )
        val acronymAdapter: ArrayAdapter<String> = ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, acronym)
        acronymSpinner.setAdapter(acronymAdapter)
    }


}