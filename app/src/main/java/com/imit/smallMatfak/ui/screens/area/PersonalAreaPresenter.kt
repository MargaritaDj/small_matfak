package com.imit.smallMatfak.ui.screens.area

import android.content.SharedPreferences
import com.imit.smallMatfak.R
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.ui.screens.base.presenter.BasePresenter
import com.imit.smallMatfak.ui.screens.windows.DialogChangePassword
import com.imit.smallMatfak.ui.screens.windows.DialogRules
import com.imit.smallMatfak.ui.screens.windows.IDialogUserPresenter
import com.imit.smallMatfak.ui.screens.windows.TypeDialogMenu
import com.imit.smallMatfak.ui.validator.Validator
import com.imit.smallMatfak.usecase.UserUseCase
import java.io.BufferedReader

open class PersonalAreaPresenter<V: PersonalAreaView>(private val sharedPreferences: SharedPreferences) : BasePresenter<V>(),
    IDialogUserPresenter{
    val userUseCase = UserUseCase(UserRepository())
    lateinit var user: User

    override fun showDialogMenu(typeDialogMenu: TypeDialogMenu) {
        getView()?.showDialogMenu(typeDialogMenu)
    }

    override fun showDialogRules(bufferedReader: BufferedReader) {
        getView()?.showDialogRules(bufferedReader)
    }

    override fun showDialogChangePassword() {
        getView()?.showDialogChangePassword()
    }

    override fun changePassword(oldPassword: String, newPassword: String, repeatPassword: String,
                                dialogChangePassword: DialogChangePassword) {
        if(checkValidationPassword(oldPassword, newPassword, repeatPassword,
                dialogChangePassword)){
            try {
                userUseCase.changePassword(user, oldPassword, newPassword)
                getView()?.showToastOk(dialogChangePassword.context.resources.getString(R.string.ok_changed_password))
                val editorSharedPreferences = sharedPreferences.edit()
                editorSharedPreferences.putString("userPassword", user.password)
                editorSharedPreferences.apply()
                dialogChangePassword.dismiss()
            } catch (exPassword: AppExceptionPassword) {
                dialogChangePassword.showValidationErrorOldPassword(exPassword.appErrorCode.errorString)
            } catch (ex: AppException) {
                getView()?.showToastError(ex.appErrorCode.errorString)
            }
        }
    }

    override fun getRulesPage(bufferedReader: BufferedReader): List<String> {
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString().split("|")
    }

    override fun showRulesRight(dialogRules: DialogRules) {
        dialogRules.showRulesRight()
    }

    override fun showRulesLeft(dialogRules: DialogRules) {
        dialogRules.showRulesLeft()
    }

    private fun checkValidationPassword(oldPassword: String, newPassword: String, repeatPassword: String,
                                        dialogChangePassword: DialogChangePassword): Boolean{
        var isNotError = true
        val messageOldPassword = Validator.validationPassword(oldPassword)
        val messageNewPassword = Validator.validationNewPassword(newPassword)
        val messageRepeatPassword = Validator.validationRepeatPassword(repeatPassword, newPassword)

        if(messageOldPassword.isNotEmpty()){
            dialogChangePassword.showValidationErrorOldPassword(messageOldPassword)
            isNotError = false
        }
        if(messageNewPassword.isNotEmpty()){
            dialogChangePassword.showValidationErrorNewPassword(messageNewPassword)
            isNotError = false
        }
        if(messageRepeatPassword.isNotEmpty()){
            dialogChangePassword.showValidationErrorRepeatPassword(messageRepeatPassword)
            isNotError = false
        }
        return isNotError
    }
}