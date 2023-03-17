package com.imit.smallMatfak.ui.screens.listtasks.student.view

import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface ListTasksStudentView: BaseView {
    fun openInfoTaskActivity(idTask: Int, idAnswer: Int)
    fun openAreaStudentActivity()
    fun openMenu()
    fun showTasksRight(map: MutableMap<String, List<TaskAnswerStudent>>, maxPage: Int)
    fun showTasksLeft(map: MutableMap<String, List<TaskAnswerStudent>>)

}