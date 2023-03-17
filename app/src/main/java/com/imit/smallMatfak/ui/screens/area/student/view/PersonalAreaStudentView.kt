package com.imit.smallMatfak.ui.screens.area.student.view

import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.ui.screens.area.PersonalAreaView
import com.imit.smallMatfak.ui.screens.base.view.BaseView
import com.imit.smallMatfak.ui.screens.windows.TypeDialogMenu
import java.io.BufferedReader

interface PersonalAreaStudentView: BaseView, PersonalAreaView {
    fun fillInfo(student: Student, scoreExpectation: Int, scoreCurrent: Int)
    fun showDialogHero()
    fun showDialogWriteCodeRoom()
    fun changeImageHero(newImageHero: Int)
    fun openListTasksStudentActivity()
}