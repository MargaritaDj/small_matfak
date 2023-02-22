package com.imit.smallMatfak.screens.windows

import android.app.Dialog
import com.imit.smallMatfak.screens.adapter.ListAdapter
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.R
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.repositories.StudentRepository
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.screens.adapter.ImageAdapter
import com.imit.smallMatfak.screens.dismissDialogWindow
import com.imit.smallMatfak.usecase.StudentUseCase
import com.imit.smallMatfak.usecase.UserUseCase
import com.imit.smallMatfak.utils.UtilsView
import com.imit.smallMatfak.validator.Validator
import java.io.BufferedReader


class DialogWindowsPersonalArea(val context: Context, private val layout: View, val user: User) {
    private val studentUseCase = StudentUseCase(StudentRepository())
    private val userUseCase = UserUseCase(UserRepository())

    private val itemsSettings: List<String> = listOf(context.resources.getString(R.string.change_password),
        context.resources.getString(R.string.change_hero))


    fun showDialogMenu(dialogMenu: Dialog, typeDialogMenu: TypeDialogMenu) {

        val itemsMenu = when(typeDialogMenu){
            TypeDialogMenu.SETTINGS -> itemsSettings
        }

        dialogMenu.setContentView(R.layout.window_menu)
        dialogMenu.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogMenu.setCanceledOnTouchOutside(false)
        dialogMenu.show()
        val changeListView: ListView =
            dialogMenu.findViewById(R.id.menu_list)
        val chooseButton: ImageButton = dialogMenu.findViewById(R.id.menu_choose)
        val crossSettings: ImageButton = dialogMenu.findViewById(R.id.menu_cross)

        val adapterListString = ListAdapter(context, R.layout.menu_list_item, itemsMenu)
        changeListView.adapter = adapterListString

        changeListView.setOnItemClickListener { _, _, pos, _ ->
            adapterListString.setSelectedPosition(pos)
            adapterListString.notifyDataSetChanged()
        }

        chooseButton.setOnClickListener {
            val itemSelected = adapterListString.getSelectedString()
            when(typeDialogMenu){
                TypeDialogMenu.SETTINGS -> {
                    if (itemSelected == itemsMenu[0]) {
                        showDialogChangePassword(dialogMenu)
                    }
                }
            }
        }

        dismissDialogWindow(dialogMenu, crossSettings)
    }

    private fun showDialogChangePassword(dialogSettings: Dialog) {
        dialogSettings.setContentView(R.layout.window_change_password)
        val crossChange: ImageButton = dialogSettings.findViewById(R.id.change_password_cross)
        val changeButton: ImageButton = dialogSettings.findViewById(R.id.change_password_button)
        val backButton: ImageButton = dialogSettings.findViewById(R.id.change_password_back)

        val oldPassword: EditText = dialogSettings.findViewById(R.id.change_password_old_edit_text)
        val newPassword: EditText = dialogSettings.findViewById(R.id.change_password_new_edit_text)
        val repeatPassword: EditText =
            dialogSettings.findViewById(R.id.change_password_repeat_edit_text)

        val oldPasswordLayout: TextInputLayout =
            dialogSettings.findViewById(R.id.change_password_old)
        val newPasswordLayout: TextInputLayout =
            dialogSettings.findViewById(R.id.change_password_new)
        val repeatPasswordLayout: TextInputLayout =
            dialogSettings.findViewById(R.id.change_password_repeat)

        val oldEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye1)
        val newEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye2)
        val repeatEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye3)

        UtilsView.removeErrorOnFocus(oldPassword, oldPasswordLayout)
        UtilsView.removeErrorOnFocus(newPassword, newPasswordLayout)
        UtilsView.removeErrorOnFocus(repeatPassword, repeatPasswordLayout)

        UtilsView.changePasswordVisibility(oldPassword, oldEye)
        UtilsView.changePasswordVisibility(newPassword, newEye)
        UtilsView.changePasswordVisibility(repeatPassword, repeatEye)

        changeButton.setOnClickListener {
            val valOldPassword = Validator.validationPassword(oldPasswordLayout, oldPassword)
            val valNewPassword = Validator.validationNewPassword(newPasswordLayout, newPassword)
            val valRepeatPassword = Validator.validationRepeatPassword(
                repeatPasswordLayout,
                repeatPassword, newPassword
            )

            if (valNewPassword && valOldPassword && valRepeatPassword) {
                try {
                    userUseCase.changePassword(user, oldPassword.text.toString(),
                        newPassword.text.toString())
                    Toast.showToastOk(layout, context.resources.getString(R.string.ok_changed_password), context)
                    dialogSettings.dismiss()
                    val sharedPreferences: SharedPreferences = context.getSharedPreferences("APP_SHARED_PREF",
                        Context.MODE_PRIVATE)
                    val editorSharedPreferences = sharedPreferences.edit()
                    editorSharedPreferences.putString("userPassword", user.password)
                    editorSharedPreferences.apply()
                } catch (exPassword: AppExceptionPassword) {
                    oldPasswordLayout.error = exPassword.appErrorCode.errorString
                } catch (ex: AppException) {
                    Toast.showToastError(layout, ex.appErrorCode.errorString, context)
                }
            }
        }

        dismissDialogWindow(dialogSettings, crossChange)

        backButton.setOnClickListener {
            showDialogMenu(dialogSettings, TypeDialogMenu.SETTINGS)
        }
    }


    fun showDialogRules(dialogRules: Dialog, bufferedReader: BufferedReader) {
        dialogRules.setContentView(R.layout.window_rules)
        dialogRules.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogRules.setCanceledOnTouchOutside(false)
        dialogRules.show()

        val rulesText: TextView = dialogRules.findViewById(R.id.rules_text)
        val arrowLeft: ImageButton = dialogRules.findViewById(R.id.rules_arrow_left)
        val arrowRight: ImageButton = dialogRules.findViewById(R.id.rules_arrow_right)
        val crossButton: ImageButton = dialogRules.findViewById(R.id.rules_cross)

        dismissDialogWindow(dialogRules, crossButton)
        val alpha = 0.4F

        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        val stringPages = stringBuilder.toString().split("|")

        var page = 1
        rulesText.text = stringPages[page - 1]
        arrowLeft.alpha = alpha

        arrowRight.setOnClickListener {
            if (page != stringPages.size) {
                page++
                rulesText.text = stringPages[page - 1].trim()
                arrowLeft.alpha = 1F
            }

            if (page == stringPages.size) {
                arrowRight.alpha = alpha
            }
        }

        arrowLeft.setOnClickListener {
            if (page != 1) {
                page--
                rulesText.text = stringPages[page - 1].trim()
                arrowRight.alpha = 1F
            }

            if (page == 1) {
                arrowLeft.alpha = alpha
            }
        }
    }

    fun showDialogChoiceHero(
        dialogChoiceHero: Dialog, imageHeroButton: ImageButton) {
        dialogChoiceHero.setContentView(R.layout.window_choice_hero)
        dialogChoiceHero.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogChoiceHero.setCanceledOnTouchOutside(false)
        dialogChoiceHero.show()

        val gridHeroes: GridView = dialogChoiceHero.findViewById(R.id.choice_hero_grid)
        val buttonChoice: ImageButton = dialogChoiceHero.findViewById(R.id.choice_hero_button)
        val adapterImage = ImageAdapter(context = context, context.resources)
        gridHeroes.adapter = adapterImage

        gridHeroes.onItemClickListener = OnItemClickListener { _, _, pos, _ ->
            adapterImage.setSelectedPosition(pos)
            adapterImage.notifyDataSetChanged()
        }

        buttonChoice.setOnClickListener {
            val imageHero: Int = adapterImage.getSelectedImageHero() as Int
            try {
                studentUseCase.changeImageHero(user, imageHero)
                Toast.showToastOk(layout, context.resources.getString(R.string.ok_image_hero), context)
                dialogChoiceHero.dismiss()
                imageHeroButton.setBackgroundResource(user.imageHero)
            } catch (ex: AppException) {
                Toast.showToastError(layout, ex.appErrorCode.errorString, context)
            }

        }

        val crossButton: ImageButton = dialogChoiceHero.findViewById(R.id.choice_hero_cross)
        dismissDialogWindow(dialogChoiceHero, crossButton)
    }
}