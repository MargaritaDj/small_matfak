package com.imit.smallMatfak.ui.screens.main.view

import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface MainView: BaseView {
    fun showValidationMessageLogin(message: String)
    fun showValidationMessagePassword(message: String)
    fun loginAuto(userLoginSharedPreferences: String, userPasswordSharedPreferences: String)
    fun openPersonalAreaStudentActivity(token: String)
    fun openPersonalAreaTeacherActivity(token: String)
    fun openForgotPasswordActivity()
}