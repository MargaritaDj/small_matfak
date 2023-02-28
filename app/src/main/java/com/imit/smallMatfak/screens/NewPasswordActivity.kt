package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.utils.UtilsView
import com.imit.smallMatfak.validator.Validator

class NewPasswordActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        val editTextPassword: EditText = findViewById(R.id.new_password_activity_password_text)
        val buttonPassword1: ImageButton = findViewById(R.id.new_password_button_eye1)
        UtilsView.changePasswordVisibility(editTextPassword, buttonPassword1)

        val editTextPasswordRepeat: EditText =
            findViewById(R.id.new_password_activity_repeat_password_text)
        val buttonPassword2: ImageButton = findViewById(R.id.new_password_button_eye2)
        UtilsView.changePasswordVisibility(editTextPasswordRepeat, buttonPassword2)

        val layoutPassword: TextInputLayout = findViewById(R.id.new_password_activity_password)
        val layoutPasswordRepeat: TextInputLayout =
            findViewById(R.id.new_password_activity_repeat_password)

        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.removeErrorOnFocus(editTextPasswordRepeat, layoutPasswordRepeat)

        val buttonChange: ImageButton =
            findViewById(R.id.new_password_activity_change_password_button)
        buttonChange.setOnClickListener {
            val valNewPassword = Validator.validationNewPassword(layoutPassword, editTextPassword)
            val valRepeatPassword = Validator.validationRepeatPassword(
                layoutPasswordRepeat,
                editTextPasswordRepeat,
                editTextPassword
            )

            if(valNewPassword && valRepeatPassword){
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        val buttonCross: ImageButton = findViewById(R.id.new_password_activity_activity_cross)
        buttonCross.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}