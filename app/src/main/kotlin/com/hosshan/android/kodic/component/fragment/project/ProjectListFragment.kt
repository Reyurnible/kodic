package com.hosshan.android.kodic.component.fragment.project

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.adapter.ProjectAdapter
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.model.Project
import com.hosshan.android.kodic.store.codic.UserStore
import rx.Subscriber
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by shunhosaka on 15/09/12.
 */
public class ProjectListFragment : BaseFragment() {

    companion object {
        @JvmStatic public fun newInstance(): ProjectListFragment {
            val fragment: ProjectListFragment = ProjectListFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    val recyclerView: RecyclerView by bindView(R.id.project_list_recyclerview)
    var adapter: ProjectAdapter by Delegates.notNull()

    @Inject lateinit val userStore: UserStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProjectComponent.Initializer.init(activity!!).inject(this)
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
        userStore
                .getProjectList()
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