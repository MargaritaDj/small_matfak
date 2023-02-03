package com.imit.smallMatfak.database

import android.widget.EditText
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppExceptionLogin
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.User

object Database {
    var loginUsers: Map<String, User> = mutableMapOf()

    fun getUserByLogin(editTextLogin: EditText, editTextPassword: EditText): User{
        val user = loginUsers[editTextLogin.text.toString().substring(2)]
            ?: throw AppExceptionLogin(AppErrorCode.WRONG_LOGIN)

        if(user.password != editTextPassword.text.toString()){
            throw AppExceptionPassword(AppErrorCode.WRONG_PASSWORD)
        }
        return user
    }
}