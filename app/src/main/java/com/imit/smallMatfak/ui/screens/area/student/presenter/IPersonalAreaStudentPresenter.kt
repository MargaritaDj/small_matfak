package com.imit.smallMatfak.ui.screens.area.student.presenter

import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentView
import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter
import com.imit.smallMatfak.ui.screens.windows.TypeDialogMenu

interface IPersonalAreaStudentPresenter<V: PersonalAreaStudentView>: IBasePresenter<V> {
    fun fillInfoStudentByToken(token: String)
    fun logout()
}