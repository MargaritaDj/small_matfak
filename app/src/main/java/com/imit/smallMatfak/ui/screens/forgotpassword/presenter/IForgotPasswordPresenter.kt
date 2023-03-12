package com.imit.smallMatfak.ui.screens.forgotpassword.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.forgotpassword.view.ForgotPasswordView

interface IForgotPasswordPresenter<V: ForgotPasswordView>: IBasePresenter<V> {
    fun checkValidation(phone: String)
    fun back()
}