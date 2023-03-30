package com.imit.smallMatfak.ui.screens.main.presenter

import android.content.SharedPreferences
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppExceptionLogin
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.main.view.MainView
import com.imit.smallMatfak.ui.validator.Validator
import com.imit.smallMatfak.usecase.UserUseCase

class MainPresenter<V: MainView>(private val sharedPreferences: SharedPreferences)
    : BasePresenter<V>(), IMainPresenter<V> {
    private lateinit var userLoginSharedPreferences: String
    private lateinit var userPasswordSharedPreferences: String
    private val userUseCase = UserUseCase(UserRepository())


    override fun onAttach(view: V?) {
        super.onAttach(view)
        checkSharedPreferences()
    }

    override fun login(login: String, password: String){
        if(checkValidation(login, password)){
            try {
                val tokenUser = userUseCase.login(login, password)
                val user = userUseCase.getUserByToken(tokenUser)
                val editorSharedPreferences = sharedPreferences.edit()
                editorSharedPreferences.putString("userLogin", login)
                editorSharedPreferences.putString("userPassword", password)
                editorSharedPreferences.apply()
                if (user::class.java == Student::class.java) {
                    getView()?.openPersonalAreaStudentActivity(tokenUser)
                } else {
                    getView()?.openPersonalAreaTeacherActivity(tokenUser)
                }
            } catch (exLogin: AppExceptionLogin) {
                getView()?.showValidationMessageLogin(exLogin.appErrorCode.errorString)
            } catch (exPassword: AppExceptionPassword) {
                getView()?.showValidationMessagePassword(exPassword.appErrorCode.errorString)
            }
        }
    }

    override fun forgotPassword() {
        getView()?.openForgotPasswordActivity()
    }

    private fun checkValidation(login: String, password: String): Boolean{
        val messageLogin = Validator.validationLogin(login)
        val messagePassword = Validator.validationPassword(password)
        var isNotError = true

        if(messageLogin.isNotEmpty()){
            getView()?.showValidationMessageLogin(messageLogin)
            isNotError = false
        }
        if(messagePassword.isNotEmpty()){
            getView()?.showValidationMessagePassword(messagePassword)
            isNotError = false
        }
        return isNotError
    }

    private fun checkSharedPreferences(){
        userLoginSharedPreferences = sharedPreferences.getString("userLogin", "") ?: ""
        userPasswordSharedPreferences = sharedPreferences.getString("userPassword", "") ?: ""
        if(userLoginSharedPreferences.isNotEmpty() && userPasswordSharedPreferences.isNotEmpty()){
            getView()?.loginAuto(userLoginSharedPreferences, userPasswordSharedPreferences)
        }
    }
}