package com.imit.smallMatfak.ui.screens.abouttask.presenter

import com.imit.smallMatfak.ui.screens.abouttask.view.AboutTaskStudentView
import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter

interface IAboutTaskStudentPresenter<V: AboutTaskStudentView>: IBasePresenter<V> {
    fun changeInfoAboutTask(idTask: Int, idAnswer: Int)
    fun backArea()
    fun openListTasks()
    fun openSolveTask()
}