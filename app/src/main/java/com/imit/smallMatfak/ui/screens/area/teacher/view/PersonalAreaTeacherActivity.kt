package com.imit.smallMatfak.ui.screens.area.teacher.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.ui.screens.main.view.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Teacher
import com.imit.smallMatfak.ui.screens.area.teacher.presenter.PersonalAreaTeacherPresenter
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.windows.*
import java.io.BufferedReader
import java.io.InputStreamReader

class PersonalAreaTeacherActivity: BaseActivity(), PersonalAreaTeacherView {
    @BindView(R.id.personal_area_teacher_name) lateinit var nameTextView: TextView
    @BindView(R.id.personal_area_teacher_last_name) lateinit var lastNameTextView: TextView
    @BindView(R.id.personal_area_teacher_patronymic) lateinit var patronymicTextView: TextView
    @BindView(R.id.personal_area_teacher_play) lateinit var playButton: ImageButton
    @BindView(R.id.personal_area_teacher_tasks) lateinit var tasksButton: ImageButton
    @BindView(R.id.personal_area_teacher_regulations_game) lateinit var rulesButton: ImageButton
    @BindView(R.id.personal_area_teacher_settings) lateinit var settingsButton: ImageButton
    @BindView(R.id.personal_area_teacher_logout) lateinit var logoutButton: ImageButton

    private lateinit var layout: View
    private lateinit var presenter: PersonalAreaTeacherPresenter<PersonalAreaTeacherView>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area_teacher)
        ButterKnife.bind(this)
        token = intent.getStringExtra("token") ?: ""
        sharedPreferences = getSharedPreferences("APP_SHARED_PREF", Context.MODE_PRIVATE)
        val inflater = layoutInflater
        val container = findViewById<ViewGroup>(R.id.custom_toast_container)
        layout = inflater.inflate(R.layout.custom_toast, container)
        setOnClickListener()
        presenter = PersonalAreaTeacherPresenter(sharedPreferences)
        presenter.onAttach(this)
        presenter.fillInfoTeacherByToken(token)
    }

    override fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun fillInfo(teacher: Teacher) {
        nameTextView.text = teacher.firstName
        lastNameTextView.text = teacher.lastName
        patronymicTextView.text = teacher.patronymic
    }

    override fun showDialogMenu(typeDialogMenu: TypeDialogMenu) {
        DialogMenu(this, presenter).showDialogMenu(typeDialogMenu)
    }

    override fun showDialogRules(bufferedReader: BufferedReader) {
        DialogRules(this, presenter).showDialogRules(bufferedReader)
    }

    override fun showDialogChangePassword() {
        DialogChangePassword(this, presenter).showDialogChangePassword()
    }

    override fun showToastError(message: String) {
        Toast.showToastError(layout, message, this)
    }

    override fun showToastOk(message: String) {
        Toast.showToastOk(layout, message, this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setOnClickListener(){
        logoutButton.setOnClickListener { presenter.logout() }
        settingsButton.setOnClickListener { presenter.showDialogMenu(TypeDialogMenu.SETTINGS) }
        rulesButton.setOnClickListener {
            val bufferedReader = BufferedReader(InputStreamReader(assets.open("rules_game")))
            presenter.showDialogRules(bufferedReader)
        }
        tasksButton.setOnClickListener { presenter.showDialogMenu(TypeDialogMenu.TASKS) }
        playButton.setOnClickListener { presenter.showDialogMenu(TypeDialogMenu.PLAY_TEACHER) }
    }
}