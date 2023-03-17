package com.imit.smallMatfak.repositories

import com.imit.smallMatfak.dao.StudentDao
import com.imit.smallMatfak.database.Database
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.TaskAnswerStudent

class StudentRepository: StudentDao {
    fun changeImageHero(student: Student, imageHero: Int){
        student.imageHero = imageHero
    }

    override fun getMapDataTasks(student: Student): MutableMap<String, List<TaskAnswerStudent>> {
        return Database.getMapDataTasks(student)
    }

}