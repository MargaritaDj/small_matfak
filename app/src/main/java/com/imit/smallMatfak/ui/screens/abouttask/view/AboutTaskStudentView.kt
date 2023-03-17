package com.imit.smallMatfak.ui.screens.abouttask.view

import com.imit.smallMatfak.model.Answer
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface AboutTaskStudentView: BaseView {
    fun openAreaStudentActivity()
    fun openListTaskStudentActivity()
    fun openSolveTaskActivity()
    fun changeInfoAboutTask(task: Task, answer: Answer)
}