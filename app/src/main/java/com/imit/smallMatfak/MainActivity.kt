package com.imit.smallMatfak

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.model.Student
import com.imit.smallMatfak.screens.ForgotPasswordActivity
import com.imit.smallMatfak.utils.UtilsView
import com.imit.smallMatfak.validator.Validator

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForgotPassword: Button = findViewById(R.id.main_activity_forgot_password)
        buttonForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        val buttonEye: ImageButton = findViewById(R.id.main_activity_button_eye)

        val layoutLogin: TextInputLayout = findViewById(R.id.main_activity_phone)
        val editTextLogin:EditText = findViewById(R.id.main_activity_phone_text)
        UtilsView.removeErrorOnFocus(editTextLogin, layoutLogin)
        UtilsView.startPhone(editTextLogin)

        val layoutPassword: TextInputLayout = findViewById(R.id.main_activity_password)
        val editTextPassword: EditText = findViewById(R.id.main_activity_password_text)
        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.changePasswordVisibility(editTextPassword, buttonEye)

        val buttonLogin: Button = findViewById(R.id.main_activity_login_button)
        buttonLogin.setOnClickListener {
            Validator.validationLogin(layoutLogin, editTextLogin)
            Validator.validationPassword(layoutPassword, editTextPassword)
        }
    }
}