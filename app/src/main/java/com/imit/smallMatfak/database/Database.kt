package com.imit.smallMatfak.database

import android.widget.EditText
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.exceptions.AppExceptionLogin
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.model.User
import java.util.UUID

object Database {
    var loginUsers: MutableMap<String, User> = mutableMapOf()
    var tokenUsers: MutableMap<String, User> = mutableMapOf()
    var commonTasks: Set<Task> = mutableSetOf()
    var teacherTasks: MutableMap<String, List<Task>> = mutableMapOf()

    fun getUserByLogin(login: String): User {
        return loginUsers[login.substring(2)]
            ?: throw AppExceptionLogin(AppErrorCode.WRONG_LOGIN)
    }

    fun getUserByToken(token: String): User {
        return tokenUsers[token]
            ?: throw AppException(AppErrorCode.NOT_CONTAINS_TOKEN)
    }

    fun addTokenUser(user: User): String {
        val token: String = UUID.randomUUID().toString()
        tokenUsers.put(token, user)
        return token
    }

    fun deleteTokenUser(token: String){
        if(tokenUsers.remove(token) == null) {
            throw AppException(AppErrorCode.NOT_CONTAINS_TOKEN)
        }
    }
}