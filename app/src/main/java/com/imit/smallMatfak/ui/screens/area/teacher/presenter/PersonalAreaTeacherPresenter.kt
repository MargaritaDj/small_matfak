package com.imit.smallMatfak.ui.screens.area.teacher.presenter

import android.content.SharedPreferences
import com.imit.smallMatfak.model.Teacher
import com.imit.smallMatfak.ui.screens.area.PersonalAreaPresenter
import com.imit.smallMatfak.ui.screens.area.teacher.view.PersonalAreaTeacherView

class PersonalAreaTeacherPresenter<V: PersonalAreaTeacherView>(private val sharedPreferences: SharedPreferences):
    PersonalAreaPresenter<V>(sharedPreferences), IPersonalAreaTeacherPresenter<V>{
    private lateinit var teacher: Teacher

    override fun fillInfoTeacherByToken(token: String) {
        teacher = userUseCase.getUserByToken(token) as Teacher
        user = teacher
        getView()?.fillInfo(teacher)
    }

    override fun logout() {
        val editorSharedPreferences = sharedPreferences.edit()
        editorSharedPreferences.remove("userLogin")
        editorSharedPreferences.remove("userPassword")
        editorSharedPreferences.apply()
        getView()?.openMainActivity()
    }

}