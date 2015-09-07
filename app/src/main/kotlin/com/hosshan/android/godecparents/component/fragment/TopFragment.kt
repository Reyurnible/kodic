package com.hosshan.android.godicparents.component.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import com.hosshan.android.godicparents.R
=======
import com.hosshan.android.godecparents.R
import kotlin.platform.platformStatic
>>>>>>> BaseAcitivityを追加


/**
 * Created by shunhosaka on 15/09/06.
 */
public class TopFragment : Fragment() {

    companion object {
        platformStatic public fun createFragment(): TopFragment {
            val fragment: TopFragment = TopFragment()
            val args: Bundle = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_top, container, false)
        

        return rootView
    }


}
