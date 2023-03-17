package com.imit.smallMatfak.ui.screens.listtasks.student.presenter

import android.content.SharedPreferences
import android.os.Bundle
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.repositories.StudentRepository
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.listtasks.student.view.ListTasksStudentView
import com.imit.smallMatfak.usecase.StudentUseCase
import com.imit.smallMatfak.usecase.UserUseCase

class ListTasksStudentPresenter<V: ListTasksStudentView>(private val sharedPreferences: SharedPreferences):
    BasePresenter<V>(), IListTasksStudentPresenter<V> {
    private val studentUseCase = StudentUseCase(StudentRepository())
    private val userUseCase = UserUseCase(UserRepository())
    private lateinit var student: Student

    override fun getTasksAnswerStudent(token: String):
            MutableMap<String, List<TaskAnswerStudent>> {
        student = userUseCase.getUserByToken(token) as Student
        return studentUseCase.getMapDataTasks(student)
    }

    override fun showTasksRight(map: MutableMap<String, List<TaskAnswerStudent>>) {
        val values = map.values
        var sum = 0
        for(list in values){
            sum += list.size
        }
        val maxPage = sum / 3 + (sum % 3 > 0).compareTo(false)
        getView()?.showTasksRight(map, maxPage)
    }

    override fun showTasksLeft(map: MutableMap<String, List<TaskAnswerStudent>>) {
        getView()?.showTasksLeft(map)
    }

    override fun backArea() {
        getView()?.openAreaStudentActivity()
    }

    override fun backMenu() {
        getView()?.openMenu()
    }

    override fun openInfoTask(idTask: Int, idAnswer: Int, page: Int) {
        val editorSharedPreferences = sharedPreferences.edit()
        val currentPage = page-1
        editorSharedPreferences.putInt("pageListTasks", currentPage)
        editorSharedPreferences.apply()
        getView()?.openInfoTaskActivity(idTask, idAnswer)
    }
}