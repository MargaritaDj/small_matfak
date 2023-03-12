package com.imit.smallMatfak.ui.screens.area.student.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.ui.screens.main.view.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.ui.screens.area.student.presenter.PersonalAreaStudentPresenter
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.windows.*
import com.imit.smallMatfak.ui.screens.windows.Toast
import java.io.BufferedReader
import java.io.InputStreamReader


class PersonalAreaStudentActivity : BaseActivity(), PersonalAreaStudentView {
    @BindView(R.id.personal_area_student_name) lateinit var nameTextView: TextView
    @BindView(R.id.personal_area_student_last_name) lateinit var lastNameTextView: TextView
    @BindView(R.id.personal_area_student_level) lateinit var levelTextView: TextView
    @BindView(R.id.personal_area_student_level_count) lateinit var levelCountTextView: TextView
    @BindView(R.id.personal_area_student_score) lateinit var scoreTextView: TextView
    @BindView(R.id.personal_area_student_progress_bar) lateinit var progressBar: ProgressBar
    @BindView(R.id.personal_area_student_settings) lateinit var settingsButton: ImageButton
    @BindView(R.id.personal_area_student_regulations_game) lateinit var rulesButton: ImageButton
    @BindView(R.id.personal_area_student_logout) lateinit var logoutButton: ImageButton
    @BindView(R.id.personal_area_student_hero) lateinit var heroButton: ImageButton
    @BindView(R.id.personal_area_student_play) lateinit var playButton: ImageButton

    private lateinit var layout: View
    private lateinit var presenter: PersonalAreaStudentPresenter<PersonalAreaStudentView>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area_student)
        ButterKnife.bind(this)
        token = intent.getStringExtra("token") ?: ""
        sharedPreferences = getSharedPreferences("APP_SHARED_PREF", Context.MODE_PRIVATE)
        val inflater = layoutInflater
        val container = findViewById<ViewGroup>(R.id.custom_toast_container)
        layout = inflater.inflate(R.layout.custom_toast, container)
        setOnClickListener()
        presenter = PersonalAreaStudentPresenter(sharedPreferences)
        presenter.onAttach(this)
        presenter.fillInfoStudentByToken(token)
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun fillInfo(student: Student, scoreExpectation: Int, scoreCurrent: Int) {
        nameTextView.text = student.firstName
        lastNameTextView.text = student.lastName
        heroButton.setBackgroundResource(student.imageHero)
        levelTextView.text = String.format(resources.getString(R.string.level) + student.level)
        levelCountTextView.text = String.format("$scoreCurrent/$scoreExpectation")
        scoreTextView.text = String.format(resources.getString(R.string.score) + student.score)

        progressBar.max = scoreExpectation
        progressBar.progress = scoreCurrent
    }

    override fun showDialogMenu(typeDialogMenu: TypeDialogMenu) {
        DialogMenu(this, presenter).showDialogMenu(typeDialogMenu)
    }

    override fun showDialogRules(bufferedReader: BufferedReader) {
        DialogRules(this, presenter).showDialogRules(bufferedReader)
    }

    override fun showDialogHero() {
        DialogChoiceHero(this, presenter).showDialogChoiceHero()
    }

    override fun showDialogChangePassword() {
        DialogChangePassword(this, presenter).showDialogChangePassword()
    }

    override fun showDialogWriteCodeRoom() {
        DialogCodeRoom(this, presenter).showDialogWriteCodeRoom()
    }

    override fun showToastError(message: String) {
        Toast.showToastError(layout, message, this)
    }

    override fun showToastOk(message: String) {
        Toast.showToastOk(layout, message, this)
    }

    override fun changeImageHero(newImageHero: Int) {
        heroButton.setBackgroundResource(newImageHero)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setOnClickListener(){
        logoutButton.setOnClickListener { presenter.logout() }
        playButton.setOnClickListener { presenter.showDialogMenu(TypeDialogMenu.PLAY_STUDENT) }
        settingsButton.setOnClickListener { presenter.showDialogMenu(TypeDialogMenu.SETTINGS) }
        rulesButton.setOnClickListener {
            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            presenter.showDialogRules(bufferedReader)
        }
        heroButton.setOnClickListener { presenter.showDialogHero() }
    }
}

