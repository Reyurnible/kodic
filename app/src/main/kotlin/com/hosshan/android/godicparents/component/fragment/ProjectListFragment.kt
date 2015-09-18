package com.hosshan.android.godicparents.component.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.hosshan.android.godicparents.R
import com.hosshan.android.godicparents.component.adapter.ProjectAdapter
import com.hosshan.android.godicparents.model.Project
import com.hosshan.android.godicparents.store.ProjectStoreAdapter
import rx.Subscriber
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectListFragment : BaseFragment() {

    companion object {
        platformStatic public fun newInstance(): ProjectListFragment {
            val fragment: ProjectListFragment = ProjectListFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    val recyclerView: RecyclerView by bindView(R.id.project_list_recyclerview)
    var adapter: ProjectAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProjectAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_project_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        getProjectList()
    }

    private fun getProjectList() {
        ProjectStoreAdapter
                .getProjectList(activity)
                .observeOn(AndroidSchedulers.mainThread())
                .lift(OperatorAddToCompositeSubscription<List<Project>>(compositeSubscription))
                .subscribe(object : Subscriber<List<Project>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onNext(items: List<Project>?) {
                        adapter.addAll(items)
                    }
                })
    }

}