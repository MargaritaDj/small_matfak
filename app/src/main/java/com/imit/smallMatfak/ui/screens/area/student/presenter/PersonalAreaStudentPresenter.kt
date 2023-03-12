package com.imit.smallMatfak.ui.screens.area.student.presenter

import android.content.SharedPreferences
import com.imit.smallMatfak.R
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.repositories.StudentRepository
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.area.PersonalAreaPresenter
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentView
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.windows.*
import com.imit.smallMatfak.ui.validator.Validator
import com.imit.smallMatfak.usecase.StudentUseCase
import com.imit.smallMatfak.usecase.UserUseCase
import java.io.BufferedReader

class PersonalAreaStudentPresenter<V: PersonalAreaStudentView>(private val sharedPreferences: SharedPreferences):
    PersonalAreaPresenter<V>(sharedPreferences), IPersonalAreaStudentPresenter<V>, IDialogStudentPresenter{
    private val studentUseCase = StudentUseCase(StudentRepository())
    private lateinit var student: Student

    override fun fillInfoStudentByToken(token: String) {
        student = userUseCase.getUserByToken(token) as Student
        user = student
        val scoreExpectation = 7 + student.level - 1
        val scoreCurrent = scoreExpectation - (((7 + scoreExpectation) / 2) * student.level - student.score)
        getView()?.fillInfo(student, scoreExpectation, scoreCurrent)
    }

    override fun logout() {
        val editorSharedPreferences = sharedPreferences.edit()
        editorSharedPreferences.remove("userLogin")
        editorSharedPreferences.remove("userPassword")
        editorSharedPreferences.apply()
        getView()?.openMainActivity()
    }

    override fun showDialogHero() {
        getView()?.showDialogHero()
    }

    override fun showDialogWriteCodeRoom() {
        getView()?.showDialogWriteCodeRoom()
    }

    override fun changeImageHero(imageHero: Int, dialogChoiceHero: DialogChoiceHero) {
        try {
            studentUseCase.changeImageHero(user, imageHero)
            getView()?.showToastOk(dialogChoiceHero.context.resources.getString(R.string.ok_image_hero))
            getView()?.changeImageHero(user.imageHero)
        } catch (ex: AppException) {
            getView()?.showToastError(ex.appErrorCode.errorString)
        }
    }


    override fun showHeroesRight(dialogChoiceHero: DialogChoiceHero, arrayHeroes: ArrayList<Int>) {
        val maxPage = arrayHeroes.size/4 + (arrayHeroes.size % 4 > 0).compareTo(false)
        dialogChoiceHero.showHeroesRight(maxPage)
    }

    override fun showHeroesLeft(dialogChoiceHero: DialogChoiceHero) {
        dialogChoiceHero.showHeroesLeft()
    }

}