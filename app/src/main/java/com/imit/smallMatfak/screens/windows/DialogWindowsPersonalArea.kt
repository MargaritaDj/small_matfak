package com.imit.smallMatfak.screens.windows

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import com.imit.smallMatfak.screens.windows.Toast
import com.imit.smallMatfak.R
import com.imit.smallMatfak.exceptions.AppException
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.repositories.StudentRepository
import com.imit.smallMatfak.screens.adapter.ImageAdapter
import com.imit.smallMatfak.screens.dismissDialogWindow
import com.imit.smallMatfak.usecase.StudentUseCase
import com.imit.smallMatfak.utils.UtilsView
import java.io.BufferedReader


class DialogWindowsPersonalArea(val context: Context) {
    val studentUseCase = StudentUseCase(StudentRepository())

    fun showDialogSettings(dialogSettings: Dialog){
        dialogSettings.setContentView(R.layout.window_settings)
        dialogSettings.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogSettings.setCanceledOnTouchOutside(false)
        dialogSettings.show()
        val changeTextView: TextView =
            dialogSettings.findViewById(R.id.settings_change_password)
        val chooseButton: ImageButton = dialogSettings.findViewById(R.id.settings_choose)
        val crossSettings: ImageButton = dialogSettings.findViewById(R.id.settings_cross)
        changeTextView.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.small_arrow_settings,
            0,
            0,
            0
        )
        changeTextView.tag = 1
        chooseButton.setOnClickListener {
            if (changeTextView.tag == 1) {
                showDialogChangePassword(dialogSettings)
            }
        }
        dismissDialogWindow(dialogSettings, crossSettings)
    }

    private fun showDialogChangePassword(dialogSettings: Dialog){
        dialogSettings.setContentView(R.layout.window_change_password)
        val crossChange: ImageButton = dialogSettings.findViewById(R.id.change_password_cross)
        val oldPassword: EditText = dialogSettings.findViewById(R.id.change_password_old_edit_text)
        val newPassword: EditText = dialogSettings.findViewById(R.id.change_password_new_edit_text)
        val repeatPassword: EditText = dialogSettings.findViewById(R.id.change_password_repeat_edit_text)

        val oldEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye1)
        val newEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye2)
        val repeatEye: ImageButton = dialogSettings.findViewById(R.id.change_password_eye3)

        UtilsView.changePasswordVisibility(oldPassword, oldEye)
        UtilsView.changePasswordVisibility(newPassword, newEye)
        UtilsView.changePasswordVisibility(repeatPassword, repeatEye)

        dismissDialogWindow(dialogSettings, crossChange)
    }



    fun showDialogRules(dialogRules: Dialog, bufferedReader: BufferedReader){
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

            if(page == stringPages.size){
                arrowRight.alpha = alpha
            }
        }

        arrowLeft.setOnClickListener {
            if (page != 1) {
                page--
                rulesText.text = stringPages[page - 1].trim()
                arrowRight.alpha = 1F
            }

            if(page == 1){
                arrowLeft.alpha = alpha
            }
        }
    }

    fun showDialogChoiceHero(dialogChoiceHero: Dialog, resources: Resources,
                             layout: View, user: User, imageHeroButton: ImageButton){
        dialogChoiceHero.setContentView(R.layout.window_choice_hero)
        dialogChoiceHero.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogChoiceHero.setCanceledOnTouchOutside(false)
        dialogChoiceHero.show()

        val gridHeroes: GridView = dialogChoiceHero.findViewById(R.id.choice_hero_grid)
        val buttonChoice: ImageButton = dialogChoiceHero.findViewById(R.id.choice_hero_button)
        val adapterImage = ImageAdapter(context = context, resources)
        gridHeroes.adapter = adapterImage

        gridHeroes.onItemClickListener = OnItemClickListener { _, _, pos, _ ->
            adapterImage.setSelectedPosition(pos)
            adapterImage.notifyDataSetChanged()
        }

        buttonChoice.setOnClickListener {
            val imageHero: Int = adapterImage.getSelectedImageHero() as Int
            try{
                studentUseCase.changeImageHero(user, imageHero)
                Toast.showToastOk(layout, resources.getString(R.string.ok_image_hero), context)
                dialogChoiceHero.dismiss()
                imageHeroButton.setBackgroundResource(user.imageHero)
            } catch (ex: AppException){
                Toast.showToastError(layout, ex.appErrorCode.errorString, context)
            }

        }

        val crossButton: ImageButton = dialogChoiceHero.findViewById(R.id.choice_hero_cross)
        dismissDialogWindow(dialogChoiceHero, crossButton)
    }
}