package com.imit.smallMatfak.ui.screens.area.teacher.presenter

import com.imit.smallMatfak.ui.screens.area.teacher.view.PersonalAreaTeacherView
import com.imit.smallMatfak.ui.screens.base.presenter.IBasePresenter

interface IPersonalAreaTeacherPresenter<V: PersonalAreaTeacherView>: IBasePresenter<V> {
    fun fillInfoTeacherByToken(token: String)
    fun logout()
}