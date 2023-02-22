package com.imit.smallMatfak.repositories

import com.imit.smallMatfak.dao.UserDao
import com.imit.smallMatfak.database.Database
import com.imit.smallMatfak.model.User

class UserRepository: UserDao {
    override fun getUserByLogin(login: String): User {
        return Database.getUserByLogin(login)
    }

    override fun getUserByToken(token: String): User {
        return Database.getUserByToken(token)
    }

    override fun addTokenUser(user: User): String {
        return Database.addTokenUser(user)
    }

    override fun deleteTokenUser(token: String) {
        Database.deleteTokenUser(token)
    }

    override fun changePassword(user: User, newPassword: String) {
        user.password = newPassword
    }
}