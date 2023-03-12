package com.imit.smallMatfak.ui.screens.windows

interface IDialogStudentPresenter {
    fun showDialogHero()
    fun showDialogWriteCodeRoom()
    fun changeImageHero(imageHero: Int, dialogChoiceHero: DialogChoiceHero)
    fun showHeroesRight(dialogChoiceHero: DialogChoiceHero, arrayHeroes: ArrayList<Int>)
    fun showHeroesLeft(dialogChoiceHero: DialogChoiceHero)
}