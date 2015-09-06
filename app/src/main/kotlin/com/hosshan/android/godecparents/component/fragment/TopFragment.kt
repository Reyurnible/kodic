package com.hosshan.android.godecparents.component.fragment

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by shunhosaka on 15/09/06.
 */
public class TopFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_top, container, false)




        return rootView
    }


}
