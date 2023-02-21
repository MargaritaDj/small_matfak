package com.imit.smallMatfak.repositories

import com.imit.smallMatfak.dao.StudentDao
import com.imit.smallMatfak.model.Student

class StudentRepository: StudentDao {
    fun changeImageHero(student: Student, imageHero: Int){
        student.imageHero = imageHero
    }
}