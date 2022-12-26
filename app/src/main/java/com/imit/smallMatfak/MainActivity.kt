package com.imit.smallMatfak

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.imit.smallMatfak.screens.ForgotPasswordActivity
import com.imit.smallMatfak.utils.UtilsView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForgotPassword: Button = findViewById(R.id.main_activity_forgot_password)
        buttonForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        val editTextLogin: EditText = findViewById(R.id.main_activity_phone)
        UtilsView.startPhone(editTextLogin)

        val buttonEye: ImageButton = findViewById(R.id.main_activity_button_eye)
        val editTextPassword: EditText = findViewById(R.id.main_activity_password)
        UtilsView.changePasswordVisibility(editTextPassword, buttonEye)
    }
}