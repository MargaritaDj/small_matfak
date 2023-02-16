package com.imit.smallMatfak.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppException
import java.util.regex.Pattern

object Validator {

    fun validationLogin(loginLayout: TextInputLayout, login: EditText): Boolean{
        val textLogin = login.text.toString()

        if(textLogin.length == 2 || textLogin.isEmpty()){
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

    fun validationPhone(phoneLayout: TextInputLayout, phone: EditText): Boolean{
        val textPhone = phone.text.toString()

        if(textPhone.length == 2 || textPhone.isEmpty()){
            phoneLayout.error = AppErrorCode.EMPTY_PHONE.errorString
            return false
        }
        if(!Pattern.matches("\\+7[0-9]{10}", textPhone)){
            phoneLayout.error = AppErrorCode.WRONG_PHONE.errorString
            return false
        }
        return true
    }

    fun validationCell(cellLayout: TextInputLayout, cell: EditText): Boolean{
        val textCell = cell.text.toString()

        if(textCell.isEmpty()){
            cellLayout.error = AppErrorCode.WRONG_CODE_FROM_SMS.errorString
            return false
        }

        return true
    }

    fun validationNewPassword(passwordLayout: TextInputLayout, password: EditText):Boolean{
        val textPassword = password.text.toString()

        if(textPassword.isEmpty()){
            passwordLayout.error = AppErrorCode.EMPTY_PASSWORD.errorString
            return false
        }
        if(textPassword.length < 6){
            passwordLayout.error = AppErrorCode.SHORT_PASSWORD.errorString
            return false
        }
        return true
    }
}