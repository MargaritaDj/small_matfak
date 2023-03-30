package com.imit.smallMatfak.ui.screens.area.teacher.view

import com.imit.smallMatfak.model.Teacher
import com.imit.smallMatfak.ui.screens.area.PersonalAreaView
import com.imit.smallMatfak.ui.screens.base.view.BaseView

interface PersonalAreaTeacherView: BaseView, PersonalAreaView {
    fun fillInfo(teacher: Teacher)
}