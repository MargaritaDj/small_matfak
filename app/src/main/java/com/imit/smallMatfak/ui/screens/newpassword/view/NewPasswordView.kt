package com.imit.smallMatfak.ui.screens.newpassword.view

import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface NewPasswordView: BaseView {
    fun openMainActivity()
    fun showValidationMessagePassword(message: String)
    fun showValidationMessagePasswordRepeat(message: String)
}