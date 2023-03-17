package com.imit.smallMatfak.usecase

import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.repositories.StudentRepository

class StudentUseCase(private val studentRepository: StudentRepository) {

    fun changeImageHero(user: User, imageHero: Int){
        if(user.javaClass != Student::class.java){
            throw AppException(AppErrorCode.NOT_STUDENT)
        }
        if(imageHero <= 0){
            throw AppException(AppErrorCode.NOT_IMAGE_HERO)
        }
        studentRepository.changeImageHero(user as Student, imageHero)
    }

    fun getMapDataTasks(student: Student): MutableMap<String, List<TaskAnswerStudent>>{
        return studentRepository.getMapDataTasks(student)
    }
}