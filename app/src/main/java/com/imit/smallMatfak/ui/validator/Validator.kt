package com.imit.smallMatfak.ui.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.model.User
import java.util.regex.Pattern

object Validator {

    fun validationLogin(login: String): String{
        if(login.length == 2 || login.isEmpty()){
            return AppErrorCode.EMPTY_LOGIN.errorString
        }
        if(!Pattern.matches("\\+7[0-9]{10}", login)){
           return AppErrorCode.WRONG_LOGIN.errorString
        }
        return ""
    }

    fun validationPassword(password: String): String{
        if(password.isEmpty()){
            return AppErrorCode.EMPTY_PASSWORD.errorString
        }
        if(password.length < 6){
            return AppErrorCode.WRONG_PASSWORD.errorString
        }
        return ""
    }


    fun validationPhone(phone: String): String{
        if(phone.length == 2 || phone.isEmpty()){
            return AppErrorCode.EMPTY_PHONE.errorString
        }
        if(!Pattern.matches("\\+7[0-9]{10}", phone)){
            return AppErrorCode.WRONG_PHONE.errorString
        }
        return ""
    }

    fun validationCell(cell: String): String{
        if(cell.isEmpty()){
            return AppErrorCode.WRONG_CODE_FROM_SMS.errorString
        }
        return ""
    }

    fun validationNewPassword(password: String): String{
        if(password.isEmpty()){
            return AppErrorCode.EMPTY_PASSWORD.errorString
        }
        if(password.length < 6){
            return AppErrorCode.SHORT_PASSWORD.errorString
        }
        return ""
    }

    fun validationRepeatPassword(repeatPassword: String, newPassword: String): String{
        if(repeatPassword.isEmpty()){
            return AppErrorCode.EMPTY_PASSWORD.errorString
        }
        if(repeatPassword.length < 6){
            return AppErrorCode.WRONG_PASSWORD.errorString
        }
        if(repeatPassword != newPassword){
            return AppErrorCode.WRONG_PASSWORD.errorString
        }
        return ""
    }
}