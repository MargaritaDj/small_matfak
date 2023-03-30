package com.imit.smallMatfak.dao

import com.imit.smallMatfak.model.Answer
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.model.User

interface UserDao {
   fun getUserByLogin(login: String): User
   fun getUserByToken(token: String): User
   fun addTokenUser(user: User): String
   fun deleteTokenUser(token: String)
   fun changePassword(user: User, newPassword: String)
   fun getTaskById(idTask: Int): Task
   fun getAnswerById(idAnswer: Int): Answer
}