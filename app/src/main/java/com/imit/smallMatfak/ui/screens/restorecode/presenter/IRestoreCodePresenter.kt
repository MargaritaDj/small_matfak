package com.imit.smallMatfak.ui.screens.restorecode.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.restorecode.view.RestoreCodeView

interface IRestoreCodePresenter<V: RestoreCodeView>: IBasePresenter<V> {
    fun checkValidation(textCode1: String, textCode2: String, textCode3: String,
                        textCode4: String, login: String)
    fun back()
}