package com.imit.smallMatfak.ui.screens.base.presenter

import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface IBasePresenter<V: BaseView> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
}