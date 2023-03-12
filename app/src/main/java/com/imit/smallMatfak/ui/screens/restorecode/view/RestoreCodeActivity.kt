package com.imit.smallMatfak.ui.screens.restorecode.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.R
import com.imit.smallMatfak.ui.screens.newpassword.view.NewPasswordActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.forgotpassword.view.ForgotPasswordActivity
import com.imit.smallMatfak.ui.screens.restorecode.presenter.RestoreCodePresenter
import com.imit.smallMatfak.utils.UtilsView

class RestoreCodeActivity : BaseActivity(), RestoreCodeView {
    @BindView(R.id.restore_activity_cell_code1_text) lateinit var editTextCode1: EditText
    @BindView(R.id.restore_activity_cell_code2_text) lateinit var editTextCode2: EditText
    @BindView(R.id.restore_activity_cell_code3_text) lateinit var editTextCode3: EditText
    @BindView(R.id.restore_activity_cell_code4_text) lateinit var editTextCode4: EditText

    @BindView(R.id.restore_activity_cell_code1) lateinit var layoutCode1: TextInputLayout
    @BindView(R.id.restore_activity_cell_code2) lateinit var layoutCode2: TextInputLayout
    @BindView(R.id.restore_activity_cell_code3) lateinit var layoutCode3: TextInputLayout
    @BindView(R.id.restore_activity_cell_code4) lateinit var layoutCode4: TextInputLayout

    @BindView(R.id.restore_activity_info_phone) lateinit var phoneInfoText: TextView
    @BindView(R.id.restore_activity_change_button) lateinit var buttonChange: ImageButton
    @BindView(R.id.restore_activity_back) lateinit var buttonBack: ImageButton

    private lateinit var presenter: RestoreCodePresenter<RestoreCodeView>
    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_code)
        ButterKnife.bind(this)
        phone = intent.getStringExtra("phone") ?: ""
        setInfoPhone()
        UtilsView.switchCellsCode(editTextCode1, editTextCode2, editTextCode3, editTextCode4,
            layoutCode1, layoutCode2, layoutCode3, layoutCode4)
        onClickListener()
        presenter = RestoreCodePresenter()
        presenter.onAttach(this)
    }

    override fun openNewPasswordActivity(login: String) {
        val intent = Intent(this, NewPasswordActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

    override fun openForgotPasswordActivity(){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun showErrorCell1() { layoutCode1.error = " " }
    override fun showErrorCell2() { layoutCode2.error = " " }
    override fun showErrorCell3() { layoutCode3.error = " " }
    override fun showErrorCell4() { layoutCode4.error = " " }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
    
    private fun onClickListener(){
        buttonChange.setOnClickListener {
            presenter.checkValidation(editTextCode1.text.toString(), editTextCode2.text.toString(),
                editTextCode3.text.toString(), editTextCode4.text.toString(), phone)
        }
        buttonBack.setOnClickListener { presenter.back() }
    }
    
    private fun setInfoPhone(){
        val text = phoneInfoText.text.toString() + phone
        phoneInfoText.text = text
    }
    
}