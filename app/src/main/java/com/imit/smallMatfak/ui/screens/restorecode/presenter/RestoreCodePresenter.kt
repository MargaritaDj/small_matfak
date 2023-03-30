package com.imit.smallMatfak.ui.screens.restorecode.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.restorecode.view.RestoreCodeView
import com.imit.smallMatfak.ui.validator.Validator

class RestoreCodePresenter<V: RestoreCodeView>: BasePresenter<V>(), IRestoreCodePresenter<V> {
    private lateinit var messageCode1: String
    private lateinit var messageCode2: String
    private lateinit var messageCode3: String
    private lateinit var messageCode4: String

    override fun checkValidation(
        textCode1: String,
        textCode2: String,
        textCode3: String,
        textCode4: String,
        login: String
    ) {
        messageCode1 = Validator.validationCell(textCode1)
        messageCode2 = Validator.validationCell(textCode2)
        messageCode3 = Validator.validationCell(textCode3)
        messageCode4 = Validator.validationCell(textCode4)

        if(!checkError()){
            getView()?.openNewPasswordActivity(login)
        }
    }

    override fun back() {
        getView()?.openForgotPasswordActivity()
    }

    private fun checkError(): Boolean{
        var isError = false
        if(messageCode1.isNotEmpty()){
            getView()?.showErrorCell1()
            isError = true
        }
        if(messageCode2.isNotEmpty()){
            getView()?.showErrorCell2()
            isError = true
        }
        if(messageCode3.isNotEmpty()){
            getView()?.showErrorCell3()
            isError = true
        }
        if(messageCode4.isNotEmpty()){
            getView()?.showErrorCell4()
            isError = true
        }
        return isError
    }
}