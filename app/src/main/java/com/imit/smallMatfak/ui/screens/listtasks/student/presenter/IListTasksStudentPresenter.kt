package com.imit.smallMatfak.ui.screens.listtasks.student.presenter

import android.os.Bundle
import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.listtasks.student.view.ListTasksStudentView

interface IListTasksStudentPresenter<V: ListTasksStudentView>: IBasePresenter<V> {
    fun getTasksAnswerStudent(token: String):
            MutableMap<String, List<TaskAnswerStudent>>
    fun showTasksRight(map: MutableMap<String, List<TaskAnswerStudent>>)
    fun showTasksLeft(map: MutableMap<String, List<TaskAnswerStudent>>)
    fun backArea()
    fun backMenu()
    fun openInfoTask(idTask: Int, idAnswer: Int, page: Int)
}