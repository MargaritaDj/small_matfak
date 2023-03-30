package com.imit.smallMatfak.ui.screens.solve.presenter

import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.solve.view.SolveTaskStudentView
import com.imit.smallMatfak.usecase.UserUseCase

class SolveTaskStudentPresenter<V: SolveTaskStudentView>:
    BasePresenter<V>(), ISolveTaskStudentPresenter<V> {
    private val userUseCase = UserUseCase(UserRepository())

    override fun changeInfoAboutSolve(idTask: Int, idAnswer: Int) {
        try{
            val task = userUseCase.getTaskById(idTask)
            val answer = userUseCase.getAnswerById(idAnswer)
            getView()?.changeInfoAboutSolve(task, answer)
        } catch (_: AppException){ }
    }

    override fun backArea() {
        getView()?.openAreaStudentActivity()
    }

    override fun openAboutTask() {
        getView()?.openAboutTaskActivity()
    }

}