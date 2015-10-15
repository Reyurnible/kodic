package com.hosshan.android.kodic.component.fragment.project

import android.content.Context
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
import com.hosshan.android.kodic.store.codic.CodicRequestSubscriber
import com.hosshan.android.kodic.store.codic.UserStore
import com.hosshan.android.kodic.util.addComposite
import rx.Subscriber
import rx.schedulers.Schedulers
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

    @Inject lateinit var userStore: UserStore

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ProjectComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProjectAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_project_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        getProjectList()
    }

    private fun getProjectList() {
        userStore
                .getProjectList()
                .addComposite(compositeSubscription)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CodicRequestSubscriber<List<Project>>(this) {
                    override fun onNext(items: List<Project>?) {
                        super.onNext(items)
                        adapter.addAll(items)
                    }
                })
    }

}
