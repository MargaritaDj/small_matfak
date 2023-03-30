package com.imit.smallMatfak.database

import android.widget.EditText
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.exceptions.AppExceptionLogin
import com.imit.smallMatfak.model.*
import java.util.UUID

object Database {
    val loginUsers: MutableMap<String, User> = mutableMapOf()
    val tokenUsers: MutableMap<String, User> = mutableMapOf()
    val commonTasks: MutableSet<Task> = mutableSetOf()
    val teacherTasks: MutableMap<String, List<Task>> = mutableMapOf()

    val allTasks: MutableSet<Task> = mutableSetOf()
    val answerOnTask: MutableSet<Answer> = mutableSetOf()
    val studentRooms: MutableMap<Student, List<Room>> = mutableMapOf()
    val answersStudentRoom: MutableMap<Pair<Student, Room>, List<TaskAnswerStudent>> = mutableMapOf()

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

    fun getMapDataTasks(student: Student):
            MutableMap<String, List<TaskAnswerStudent>>{
        val rooms = studentRooms[student]
        val map: MutableMap<String, List<TaskAnswerStudent>> = mutableMapOf()
        if (rooms != null) {
            for(room in rooms){
                answersStudentRoom[Pair(student, room)]?.let { map.put(room.date, it) }
            }
        }
        return map
    }

    fun getTaskById(idTask: Int): Task{
        for(task in allTasks){
            if(task.id == idTask){
                return task
            }
        }
        throw AppException(AppErrorCode.NOT_TASK)
    }

    fun getAnswerById(idAnswer: Int): Answer{
        for(answer in answerOnTask){
            if(answer.idAnswer == idAnswer){
                return answer
            }
        }
        throw AppException(AppErrorCode.NOT_ANSWER)
    }
}