package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.screens.windows.DialogWindowsPersonalArea
import com.imit.smallMatfak.utils.UtilsView
import java.io.BufferedReader
import java.io.InputStreamReader


class PersonalAreaStudentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area_student)

        val nameTextView: TextView = findViewById(R.id.personal_area_student_name)
        val lastNameTextView: TextView = findViewById(R.id.personal_area_student_last_name)

        val levelTextView: TextView = findViewById(R.id.personal_area_student_level)
        val levelCountTextView: TextView = findViewById(R.id.personal_area_student_level_count)
        val scoreTextView: TextView = findViewById(R.id.personal_area_student_score)
        val progressBar: ProgressBar = findViewById(R.id.personal_area_student_progress_bar)
        val settingsButton: ImageButton = findViewById(R.id.personal_area_student_settings)
        val rulesButton: ImageButton = findViewById(R.id.personal_area_student_regulations_game)
        val logoutButton: ImageButton = findViewById(R.id.personal_area_student_logout)

        logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val user = intent.getSerializableExtra("user") as? Student

        nameTextView.text = user?.firstName
        lastNameTextView.text = user?.lastName

        val scoreExpectation = 7 + user?.level!! - 1
        val scoreCurrent =
            scoreExpectation - (((7 + scoreExpectation) / 2) * user.level - user.score)

        levelTextView.text = resources.getString(R.string.level) + user.level
        levelCountTextView.text = "$scoreCurrent/$scoreExpectation"
        scoreTextView.text = resources.getString(R.string.score) + user.score

        progressBar.max = scoreExpectation
        progressBar.progress = scoreCurrent

        val dialogWindowsPersonalArea = DialogWindowsPersonalArea()

        settingsButton.setOnClickListener {
            val dialogSettings = Dialog(this)
            dialogWindowsPersonalArea.showDialogSettings(dialogSettings)
          /*  dialogSettings.setContentView(R.layout.window_settings)
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
            }
            dismissDialogWindow(dialogSettings, crossSettings)*/
        }


        rulesButton.setOnClickListener {
            val dialogRules = Dialog(this)
            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            dialogWindowsPersonalArea.showDialogRules(dialogRules, bufferedReader)
            /*dialogRules.setContentView(R.layout.window_rules)
            dialogRules.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogRules.setCanceledOnTouchOutside(false)
            dialogRules.show()

            val rulesText: TextView = dialogRules.findViewById(R.id.rules_text)
            val arrowLeft: ImageButton = dialogRules.findViewById(R.id.rules_arrow_left)
            val arrowRight: ImageButton = dialogRules.findViewById(R.id.rules_arrow_right)
            val crossButton: ImageButton = dialogRules.findViewById(R.id.rules_cross)

            dismissDialogWindow(dialogRules, crossButton)
            val alpha = 0.4F

            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            val stringPages = stringBuilder.toString().split("NULL")

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
            }*/
        }

    }
}

fun dismissDialogWindow(dialog: Dialog, cross: ImageButton) {
    cross.setOnClickListener {
        dialog.dismiss()
    }
}

fun showPopupSettings(context: Context, settingsMenuView: View) {
    val popup = PopupMenu(context, settingsMenuView)
    val inflater: MenuInflater = popup.menuInflater
    inflater.inflate(R.menu.popup_settings, popup.menu)
    popup.setOnMenuItemClickListener { menuItem ->
        when (menuItem.itemId) {
            R.id.menu_change_password -> {

            }
        }
        true
    }
    popup.show()
}

