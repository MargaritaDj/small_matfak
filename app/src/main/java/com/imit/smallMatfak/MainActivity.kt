package com.imit.smallMatfak

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.database.Database
import com.imit.smallMatfak.exceptions.AppErrorCode
import com.imit.smallMatfak.exceptions.AppExceptionLogin
import com.imit.smallMatfak.exceptions.AppExceptionPassword
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.model.Teacher
import com.imit.smallMatfak.model.User
import com.imit.smallMatfak.repositories.UserRepository
import com.imit.smallMatfak.screens.ForgotPasswordActivity
import com.imit.smallMatfak.screens.PersonalAreaStudentActivity
import com.imit.smallMatfak.screens.PersonalAreaTeacherActivity
import com.imit.smallMatfak.usecase.UserUseCase
import com.imit.smallMatfak.utils.UtilsView
import com.imit.smallMatfak.validator.Validator

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        addDatabase()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userUseCase = UserUseCase(UserRepository())

        val buttonForgotPassword: Button = findViewById(R.id.main_activity_forgot_password)
        buttonForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        val buttonEye: ImageButton = findViewById(R.id.main_activity_button_eye)

        val layoutLogin: TextInputLayout = findViewById(R.id.main_activity_phone)
        val editTextLogin: EditText = findViewById(R.id.main_activity_phone_text)
        UtilsView.removeErrorOnFocus(editTextLogin, layoutLogin)
        UtilsView.startPhone(editTextLogin)

        val layoutPassword: TextInputLayout = findViewById(R.id.main_activity_password)
        val editTextPassword: EditText = findViewById(R.id.main_activity_password_text)
        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.changePasswordVisibility(editTextPassword, buttonEye)

        val buttonLogin: Button = findViewById(R.id.main_activity_login_button)
        buttonLogin.setOnClickListener {
            if (Validator.validationLogin(layoutLogin, editTextLogin) &&
                Validator.validationPassword(layoutPassword, editTextPassword)
            ) {
                try {
                   // val user = Database.getUserByLogin(editTextLogin.text.toString())
                    val user = userUseCase.getUserByLogin(editTextLogin.text.toString())
                    if (user.password != editTextPassword.text.toString()) {
                        throw AppExceptionPassword(AppErrorCode.WRONG_PASSWORD)
                    }
                    if (user::class.java == Student::class.java) {
                        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, PersonalAreaTeacherActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    }
                } catch (exLogin: AppExceptionLogin) {
                    layoutLogin.error = exLogin.appErrorCode.errorString
                } catch (exPassword: AppExceptionPassword) {
                    layoutPassword.error = exPassword.appErrorCode.errorString
                }

            }
        }
    }

    private fun addDatabase() {

        val student = Student(
            "Маргарита", "Джинджолия", "Сергеевна", 0,
            "9507999649", "123456", 11, 3, 19
        )
        val teacher = Teacher(
            "Ксения", "Филина", "Евгеньевна", 0,
            "9502181359", "654321"
        )

        Database.loginUsers.put(student.login, student)
        Database.loginUsers.put(teacher.login, teacher)
    }
}