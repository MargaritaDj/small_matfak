package com.imit.smallMatfak.usecase

import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.User

class UserUseCase(private val userRepository: UserRepository) {

    fun login(login: String, password: String): String{
        val user: User = getUserByLogin(login)
        if (user.password != password) {
            throw AppExceptionPassword(AppErrorCode.WRONG_PASSWORD)
        }
        return userRepository.addTokenUser(user)
    }

    fun logout(token: String) {
        userRepository.deleteTokenUser(token)
    }

    fun getUserByToken(token: String): User{
        return userRepository.getUserByToken(token)
    }

    fun getUserByLogin(login: String): User{
        return userRepository.getUserByLogin(login)
    }

    fun changePassword(user: User, oldPassword: String, newPassword: String){
        userRepository.changePassword(user, newPassword)
    }
}