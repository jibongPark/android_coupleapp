package com.example.myapplication.logged_out

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.myapplication.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoggedOutView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), LoggedOutInteractor.LoggedOutPresenter {

    private var loginButton: Button? = null
    private val playerOneEditText: EditText? = null
    private var playerTwoEditText: EditText? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
//        playerOneEditText = findViewById<View>(R.id.player_one_name) as EditText
        playerTwoEditText = findViewById<View>(R.id.edit_text) as EditText
        loginButton = findViewById<View>(R.id.login_button) as Button
    }


    override fun loginName(): Observable<Any>? {
        return RxView.clicks(findViewById(R.id.login_button)).map {
            playerTwoEditText?.text.toString()
        }
    }
}
