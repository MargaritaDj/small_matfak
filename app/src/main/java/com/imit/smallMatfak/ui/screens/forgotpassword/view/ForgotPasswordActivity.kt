package com.imit.smallMatfak.ui.screens.forgotpassword.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.ui.screens.main.view.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.ui.screens.restorecode.view.RestoreCodeActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.forgotpassword.presenter.ForgotPasswordPresenter
import com.imit.smallMatfak.utils.UtilsView

class ForgotPasswordActivity : BaseActivity(), ForgotPasswordView {
    @BindView(R.id.forgot_activity_phone) lateinit var layoutPhone: TextInputLayout
    @BindView(R.id.forgot_activity_phone_text) lateinit var editTextPhone: EditText
    @BindView(R.id.forgot_activity_get_button) lateinit var buttonGetCode: ImageButton
    @BindView(R.id.forgot_activity_back) lateinit var buttonBack: ImageButton

    private lateinit var presenter: ForgotPasswordPresenter<ForgotPasswordView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        ButterKnife.bind(this)
        utilsTextView()
        onClickListener()
        presenter = ForgotPasswordPresenter()
        presenter.onAttach(this)
    }

    override fun openRestoreCodeActivity(phone: String) {
        val intent = Intent(this, RestoreCodeActivity::class.java)
        intent.putExtra("phone", phone)
        startActivity(intent)
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showValidationMessagePhone(message: String) {
        layoutPhone.error = message
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun utilsTextView() {
        UtilsView.startPhone(editTextPhone)
        UtilsView.removeErrorOnFocus(editTextPhone, layoutPhone)
    }

    private fun onClickListener(){
        buttonGetCode.setOnClickListener { presenter.checkValidation(editTextPhone.text.toString())}
        buttonBack.setOnClickListener { presenter.back() }
    }
}