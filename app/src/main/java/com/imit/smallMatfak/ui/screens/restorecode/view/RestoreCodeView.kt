package com.imit.smallMatfak.ui.screens.restorecode.view

import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface RestoreCodeView: BaseView {
    fun openNewPasswordActivity(login: String)
    fun openForgotPasswordActivity()
    fun showErrorCell1()
    fun showErrorCell2()
    fun showErrorCell3()
    fun showErrorCell4()
}