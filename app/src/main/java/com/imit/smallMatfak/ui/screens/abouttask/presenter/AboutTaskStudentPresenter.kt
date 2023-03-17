package com.imit.smallMatfak.ui.screens.abouttask.presenter

import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.abouttask.view.AboutTaskStudentView
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.usecase.UserUseCase

class AboutTaskStudentPresenter<V: AboutTaskStudentView>: BasePresenter<V>(),
    IAboutTaskStudentPresenter<V> {
    private val userUseCase = UserUseCase(UserRepository())

    override fun changeInfoAboutTask(idTask: Int, idAnswer: Int) {
        try{
            val task = userUseCase.getTaskById(idTask)
            val answer = userUseCase.getAnswerById(idAnswer)
            getView()?.changeInfoAboutTask(task, answer)
        } catch (_: AppException){ }
    }

    override fun backArea() {
        getView()?.openAreaStudentActivity()
    }

    override fun openListTasks() {
        getView()?.openListTaskStudentActivity()
    }

    override fun openSolveTask() {
        getView()?.openSolveTaskActivity()
    }
}