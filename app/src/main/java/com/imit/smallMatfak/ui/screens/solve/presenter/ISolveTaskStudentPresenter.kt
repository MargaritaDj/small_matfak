package com.imit.smallMatfak.ui.screens.solve.presenter

import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.solve.view.SolveTaskStudentView

interface ISolveTaskStudentPresenter<V: SolveTaskStudentView>: IBasePresenter<V> {
    fun changeInfoAboutSolve(idTask: Int, idAnswer: Int)
    fun backArea()
    fun openAboutTask()
}