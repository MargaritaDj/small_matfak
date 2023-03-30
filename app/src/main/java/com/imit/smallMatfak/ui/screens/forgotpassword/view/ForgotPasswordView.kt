package com.imit.smallMatfak.ui.screens.forgotpassword.view

import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface ForgotPasswordView: BaseView {
    fun openRestoreCodeActivity(phone: String)
    fun showValidationMessagePhone(message: String)
    fun openMainActivity()
}