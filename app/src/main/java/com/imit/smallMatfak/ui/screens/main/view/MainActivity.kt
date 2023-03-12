package com.imit.smallMatfak.ui.screens.main.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.R
import com.imit.smallMatfak.database.Database
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.Teacher
import com.imit.smallMatfak.ui.screens.forgotpassword.view.ForgotPasswordActivity
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentActivity
import com.imit.smallMatfak.ui.screens.area.teacher.view.PersonalAreaTeacherActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.main.presenter.MainPresenter
import com.imit.smallMatfak.utils.UtilsView

class MainActivity : BaseActivity(), MainView {
    @BindView(R.id.main_activity_forgot_password) lateinit var buttonForgotPassword: Button
    @BindView(R.id.main_activity_button_eye) lateinit var buttonEye: ImageButton
    @BindView(R.id.main_activity_phone) lateinit var layoutLogin: TextInputLayout
    @BindView(R.id.main_activity_phone_text) lateinit var editTextLogin: EditText
    @BindView(R.id.main_activity_password) lateinit var layoutPassword: TextInputLayout
    @BindView(R.id.main_activity_password_text) lateinit var editTextPassword: EditText
    @BindView(R.id.main_activity_login_button) lateinit var buttonLogin: ImageButton

    private lateinit var presenter: MainPresenter<MainView>

    override fun onCreate(savedInstanceState: Bundle?) {
        addDatabase()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences("APP_SHARED_PREF",
            Context.MODE_PRIVATE)
        utilsTextView()
        onClickListener()
        presenter = MainPresenter(sharedPreferences)
        presenter.onAttach(this)
    }

    override fun showValidationMessageLogin(message: String) {
        layoutLogin.error = message
    }

    override fun showValidationMessagePassword(message: String) {
        layoutPassword.error = message
    }

    override fun openPersonalAreaStudentActivity(token: String) {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun openPersonalAreaTeacherActivity(token: String) {
        val intent = Intent(this, PersonalAreaTeacherActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun loginAuto(userLoginSharedPreferences: String, userPasswordSharedPreferences: String){
        editTextLogin.setText(userLoginSharedPreferences)
        editTextPassword.setText(userPasswordSharedPreferences)
        buttonLogin.callOnClick()
    }

    override fun openForgotPasswordActivity(){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun onClickListener(){
        buttonForgotPassword.setOnClickListener { presenter.forgotPassword() }
        buttonLogin.setOnClickListener { presenter.login(editTextLogin.text.toString(),
            editTextPassword.text.toString())}
    }


    private fun utilsTextView(){
        UtilsView.removeErrorOnFocus(editTextLogin, layoutLogin)
        UtilsView.startPhone(editTextLogin)
        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.changePasswordVisibility(editTextPassword, buttonEye)
    }



    private fun addDatabase() {

        val student = Student(
            "Маргарита", "Джинджолия", "Сергеевна", R.drawable.hero_feiry,
            "9507999649", "123456", 11, 3, 19
        )
        val teacher = Teacher(
            "Ксения", "Филина", "Евгеньевна", R.drawable.hero_feiry,
            "9502181359", "654321"
        )

        Database.loginUsers.put(student.login, student)
        Database.loginUsers.put(teacher.login, teacher)
    }
}
