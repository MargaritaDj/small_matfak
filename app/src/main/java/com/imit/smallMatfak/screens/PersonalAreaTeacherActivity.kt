package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.screens.windows.DialogWindowsPersonalArea
import com.imit.smallMatfak.screens.windows.TypeDialogMenu
import java.io.BufferedReader
import java.io.InputStreamReader

class PersonalAreaTeacherActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area_teacher)

        val nameTextView: TextView = findViewById(R.id.personal_area_teacher_name)
        val lastNameTextView: TextView = findViewById(R.id.personal_area_teacher_last_name)
        val patronymicTextView: TextView = findViewById(R.id.personal_area_teacher_patronymic)

        val playButton: ImageButton = findViewById(R.id.personal_area_teacher_play)
        val tasksButton: ImageButton = findViewById(R.id.personal_area_teacher_tasks)
        val rulesButton: ImageButton = findViewById(R.id.personal_area_teacher_regulations_game)
        val settingsButton: ImageButton = findViewById(R.id.personal_area_teacher_settings)
        val logoutButton: ImageButton = findViewById(R.id.personal_area_teacher_logout)

        logoutButton.setOnClickListener {
            val sharedPreferences: SharedPreferences = getSharedPreferences("APP_SHARED_PREF",
                Context.MODE_PRIVATE)
            val editorSharedPreferences = sharedPreferences.edit()
            editorSharedPreferences.remove("userLogin")
            editorSharedPreferences.remove("userPassword")
            editorSharedPreferences.apply()
            startActivity(Intent(this, MainActivity::class.java))
        }

        val user = intent.getSerializableExtra("user") as? User

        nameTextView.text = user?.firstName!!
        lastNameTextView.text = user.lastName
        patronymicTextView.text = user.patronymic

        val inflater = layoutInflater
        val container = findViewById<ViewGroup>(R.id.custom_toast_container)
        val layout: View = inflater.inflate(R.layout.custom_toast, container)

        val dialogWindowsPersonalArea = DialogWindowsPersonalArea(this, layout, user)
        val dialog = Dialog(this)

        settingsButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogMenu(dialog, TypeDialogMenu.SETTINGS)
        }

        rulesButton.setOnClickListener {
            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            dialogWindowsPersonalArea.showDialogRules(dialog, bufferedReader)
        }

        tasksButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogMenu(dialog, TypeDialogMenu.TASKS)
        }

        playButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogMenu(dialog, TypeDialogMenu.PLAY_TEACHER)
        }
    }
}