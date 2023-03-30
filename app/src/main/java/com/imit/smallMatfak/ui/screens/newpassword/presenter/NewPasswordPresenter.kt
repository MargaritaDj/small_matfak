package com.imit.smallMatfak.ui.screens.newpassword.presenter

import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.newpassword.view.NewPasswordView
import com.imit.smallMatfak.ui.validator.Validator
import com.imit.smallMatfak.usecase.UserUseCase

class NewPasswordPresenter<V: NewPasswordView>: BasePresenter<V>(), INewPasswordPresenter<V> {
    private val userUseCase = UserUseCase(UserRepository())

    override fun changePassword(login: String, password: String, passwordRepeat: String) {
       if(checkValidation(password, passwordRepeat)){
           val user = userUseCase.getUserByLogin(login)
           userUseCase.changePassword(user, password, passwordRepeat)
           cross()
       }
    }

    override fun cross() {
        getView()?.openMainActivity()
    }

    private fun checkValidation(password: String, passwordRepeat: String): Boolean{
        val messageNewPassword = Validator.validationNewPassword(password)
        val messageRepeatPassword = Validator.validationRepeatPassword(passwordRepeat, password)
        var isNotError = true
        if(messageNewPassword.isNotEmpty()){
            getView()?.showValidationMessagePassword(messageNewPassword)
            isNotError = false
        }
        if(messageRepeatPassword.isNotEmpty()){
            getView()?.showValidationMessagePasswordRepeat(messageRepeatPassword)
            isNotError = false
        }
        return isNotError
    }

}