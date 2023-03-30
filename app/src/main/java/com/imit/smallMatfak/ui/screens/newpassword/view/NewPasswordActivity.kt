package com.imit.smallMatfak.ui.screens.newpassword.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.ui.screens.main.view.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.newpassword.presenter.NewPasswordPresenter
import com.imit.smallMatfak.utils.UtilsView
import com.imit.smallMatfak.ui.validator.Validator

class NewPasswordActivity : BaseActivity(), NewPasswordView {
    @BindView(R.id.new_password_activity_password_text) lateinit var editTextPassword: EditText
    @BindView(R.id.new_password_button_eye1) lateinit var buttonPassword1: ImageButton
    @BindView(R.id.new_password_activity_repeat_password_text) lateinit var editTextPasswordRepeat: EditText
    @BindView(R.id.new_password_button_eye2) lateinit var buttonPassword2: ImageButton
    @BindView(R.id.new_password_activity_password) lateinit var layoutPassword: TextInputLayout
    @BindView(R.id.new_password_activity_repeat_password) lateinit var layoutPasswordRepeat: TextInputLayout
    @BindView(R.id.new_password_activity_change_password_button) lateinit var buttonChange: ImageButton
    @BindView(R.id.new_password_activity_activity_cross) lateinit var buttonCross: ImageButton

    private lateinit var presenter: NewPasswordPresenter<NewPasswordView>
    private lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
        ButterKnife.bind(this)
        login = intent.getStringExtra("login") ?: ""
        utils()
        onClickListener()
        presenter = NewPasswordPresenter()
        presenter.onAttach(this)
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showValidationMessagePassword(message: String) {
        layoutPassword.error = message
    }

    override fun showValidationMessagePasswordRepeat(message: String) {
        layoutPasswordRepeat.error = message
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun utils(){
        UtilsView.changePasswordVisibility(editTextPassword, buttonPassword1)
        UtilsView.changePasswordVisibility(editTextPasswordRepeat, buttonPassword2)
        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.removeErrorOnFocus(editTextPasswordRepeat, layoutPasswordRepeat)
    }

    private fun onClickListener(){
        buttonChange.setOnClickListener {
            presenter.changePassword(login, editTextPassword.text.toString(),
            editTextPasswordRepeat.text.toString())
        }
        buttonCross.setOnClickListener { presenter.cross() }
    }
}