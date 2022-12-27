package com.imit.smallMatfak.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.exceptions.AppErrorCode
import java.util.regex.Pattern

object Validator {

    fun validationLogin(loginLayout: TextInputLayout, login: EditText): Boolean{
        val textLogin = login.text.toString()

        if(textLogin.length == 2){
            loginLayout.error = AppErrorCode.EMPTY_LOGIN.errorString
            return false
        }
        if(!Pattern.matches("\\+7[0-9]{10}", textLogin)){
            loginLayout.error = AppErrorCode.WRONG_LOGIN.errorString
            return false
        }
        return true
    }

    fun validationPassword(passwordLayout: TextInputLayout, password: EditText): Boolean{
        val textPassword = password.text.toString()

        if(textPassword.isEmpty()){
            passwordLayout.error = AppErrorCode.EMPTY_PASSWORD.errorString
            return false
        }
        if(textPassword.length < 6){
            passwordLayout.error = AppErrorCode.WRONG_PASSWORD.errorString
        }
        return true
    }


}