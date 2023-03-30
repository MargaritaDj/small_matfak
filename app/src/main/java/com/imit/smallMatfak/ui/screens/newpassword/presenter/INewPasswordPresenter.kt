package com.imit.smallMatfak.ui.screens.newpassword.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.newpassword.view.NewPasswordView

interface INewPasswordPresenter<V: NewPasswordView>: IBasePresenter<V> {
    fun changePassword(login: String, password: String, passwordRepeat: String)
    fun cross()
}