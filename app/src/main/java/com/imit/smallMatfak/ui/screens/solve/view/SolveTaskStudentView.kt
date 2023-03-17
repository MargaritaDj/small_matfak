package com.imit.smallMatfak.ui.screens.solve.view

import com.imit.smallMatfak.model.Answer
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface SolveTaskStudentView: BaseView {
    fun openAboutTaskActivity()
    fun openAreaStudentActivity()
    fun changeInfoAboutSolve(task: Task, answer: Answer)
}