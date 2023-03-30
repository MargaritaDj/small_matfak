package com.imit.smallMatfak.ui.screens.base.presenter

import com.imit.smallMatfak.ui.screens.base.view.BaseView


abstract class BasePresenter<V: BaseView> : IBasePresenter<V>{
    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        view = null
    }
}