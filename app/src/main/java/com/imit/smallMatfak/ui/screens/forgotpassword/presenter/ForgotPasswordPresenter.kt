package com.imit.smallMatfak.ui.screens.forgotpassword.presenter

import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.forgotpassword.view.ForgotPasswordView
import com.imit.smallMatfak.ui.validator.Validator
import com.imit.smallMatfak.usecase.UserUseCase

class ForgotPasswordPresenter<V: ForgotPasswordView>: BasePresenter<V>(), IForgotPasswordPresenter<V>{
    override fun checkValidation(phone: String) {
        val messagePhone = Validator.validationPhone(phone)
        if(messagePhone.isNotEmpty()){
            getView()?.showValidationMessagePhone(messagePhone)
        } else {
            try {
                UserUseCase(UserRepository()).getUserByLogin(phone)
                getView()?.openRestoreCodeActivity(phone)
            } catch (ex: AppException){
                getView()?.showValidationMessagePhone(ex.appErrorCode.errorString)
            }
        }
    }

    override fun back() {
        getView()?.openMainActivity()
    }
}