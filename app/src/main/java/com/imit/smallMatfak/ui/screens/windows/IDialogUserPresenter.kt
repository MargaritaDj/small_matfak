package com.imit.smallMatfak.ui.screens.windows

import java.io.BufferedReader

interface IDialogUserPresenter {
    fun showDialogMenu(typeDialogMenu: TypeDialogMenu)
    fun showDialogRules(bufferedReader: BufferedReader)
    fun showDialogChangePassword()
    fun changePassword(oldPassword: String, newPassword: String, repeatPassword: String,
                       dialogChangePassword: DialogChangePassword)
    fun getRulesPage(bufferedReader: BufferedReader): List<String>
    fun showRulesRight(dialogRules: DialogRules)
    fun showRulesLeft(dialogRules: DialogRules)
}