package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.screens.windows.DialogWindowsPersonalArea
import com.imit.smallMatfak.screens.windows.TypeDialogMenu
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
        val heroButton: ImageButton = findViewById(R.id.personal_area_student_hero)
        val playButton: ImageButton = findViewById(R.id.personal_area_student_play)

        logoutButton.setOnClickListener {
            val sharedPreferences: SharedPreferences = getSharedPreferences("APP_SHARED_PREF",
                Context.MODE_PRIVATE)
            val editorSharedPreferences = sharedPreferences.edit()
            editorSharedPreferences.remove("userLogin")
            editorSharedPreferences.remove("userPassword")
            editorSharedPreferences.apply()
            startActivity(Intent(this, MainActivity::class.java))
        }

        val user = intent.getSerializableExtra("user") as? Student

        nameTextView.text = user?.firstName
        lastNameTextView.text = user?.lastName
        heroButton.setBackgroundResource(user?.imageHero ?: R.drawable.hero_feiry)

        val scoreExpectation = 7 + user?.level!! - 1
        val scoreCurrent =
            scoreExpectation - (((7 + scoreExpectation) / 2) * user.level - user.score)

        levelTextView.text = resources.getString(R.string.level) + user.level
        levelCountTextView.text = "$scoreCurrent/$scoreExpectation"
        scoreTextView.text = resources.getString(R.string.score) + user.score

        progressBar.max = scoreExpectation
        progressBar.progress = scoreCurrent

        val inflater = layoutInflater
        val container = findViewById<ViewGroup>(R.id.custom_toast_container)
        val layout: View = inflater.inflate(R.layout.custom_toast, container)

        val dialogWindowsPersonalArea = DialogWindowsPersonalArea(this, layout, user)
        val dialog = Dialog(this)

        playButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogMenu(dialog, TypeDialogMenu.PLAY_STUDENT)
        }

        settingsButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogMenu(dialog, TypeDialogMenu.SETTINGS)
        }

        rulesButton.setOnClickListener {
            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            dialogWindowsPersonalArea.showDialogRules(dialog, bufferedReader)
        }

        heroButton.setOnClickListener {
            dialogWindowsPersonalArea.showDialogChoiceHero(dialog, heroButton)
        }

    }
}

