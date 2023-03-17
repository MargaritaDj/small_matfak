package com.imit.smallMatfak.dao

import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.TaskAnswerStudent

interface StudentDao {
    fun getMapDataTasks(student: Student): MutableMap<String, List<TaskAnswerStudent>>
}