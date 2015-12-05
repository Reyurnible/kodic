package com.hosshan.android.kodic.component.fragment.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import butterknife.bindView
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.hosshan.android.kodic.R
import com.hosshan.android.kodic.component.activity.MainActivity
import com.hosshan.android.kodic.component.fragment.BaseFragment
import com.hosshan.android.kodic.data.local.Token
import com.hosshan.android.kodic.store.local.TokenStore
import com.hosshan.android.kodic.util.addComposite
import com.jakewharton.rxbinding.view.enabled
import com.jakewharton.rxbinding.widget.textChanges
import javax.inject.Inject

/**
 * Created by shunhosaka on 15/09/10.
 */
public class LoginFragment : BaseFragment() {

    companion object {
        @JvmStatic public fun newInstance(): LoginFragment {
            val fragment: LoginFragment = LoginFragment()
            fragment.arguments = createArgument()
            return fragment
        }

        @JvmStatic public fun createArgument(): Bundle =
                Bundle()
    }

    val editText: EditText by bindView(R.id.login_edittext)
    val button: Button by bindView(R.id.login_button)

    @Inject lateinit var tokenStore: TokenStore

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        LoginComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Titleをセットしない
        activity?.actionBar?.title = ""
        editText.textChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    button.isEnabled = it?.isNotEmpty() ?: false
                }

        // TokenのStoreから値を取り出す
        tokenStore.getToken()
                .addComposite(compositeSubscription)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    editText.setText(it?.token)
                }
        button.setOnClickListener {
            tokenStore.setToken(Token(editText.text.toString()))
                    .addComposite(compositeSubscription)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result: Boolean ->
                                if (result) {
                                    val intent: Intent = Intent(activity, MainActivity::class.java)
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                } else {
                                    Snackbar.make(button, R.string.login_save_failed, Snackbar.LENGTH_SHORT).show();
                                }
                            },
                            { error: Throwable ->
                                error.printStackTrace()
                            },
                            {

                            }
                    )
        }
    }
}
