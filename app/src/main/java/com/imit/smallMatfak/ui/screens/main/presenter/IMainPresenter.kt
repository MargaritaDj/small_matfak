package com.imit.smallMatfak.ui.screens.main.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.main.view.MainView

interface IMainPresenter<V: MainView>: IBasePresenter<V> {
    fun login(login: String, password: String)
    fun forgotPassword()
}