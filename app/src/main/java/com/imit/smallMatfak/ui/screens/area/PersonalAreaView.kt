package com.imit.smallMatfak.ui.screens.area

import com.imit.smallMatfak.ui.screens.base.view.BaseView
import com.imit.smallMatfak.ui.screens.windows.TypeDialogMenu
import java.io.BufferedReader

interface PersonalAreaView: BaseView {
    fun openMainActivity()
    fun showDialogMenu(typeDialogMenu: TypeDialogMenu)
    fun showDialogRules(bufferedReader: BufferedReader)
    fun showDialogChangePassword()
    fun showToastError(message: String)
    fun showToastOk(message: String)
}