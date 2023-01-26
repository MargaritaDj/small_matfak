package com.imit.smallMatfak.screens

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

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val layoutPhone: TextInputLayout = findViewById(R.id.forgot_activity_phone)
        val editTextPhone: EditText = findViewById(R.id.forgot_activity_phone_text)
        UtilsView.startPhone(editTextPhone)
        UtilsView.removeErrorOnFocus(editTextPhone, layoutPhone)

        val buttonGetCode: Button = findViewById(R.id.forgot_activity_get_button)
        buttonGetCode.setOnClickListener {
            if (Validator.validationPhone(layoutPhone, editTextPhone)) {
                startActivity(Intent(this, RestoreCodeActivity::class.java))
            }
        }

        val buttonBack: ImageButton = findViewById(R.id.forgot_activity_back)
        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val buttonHome: ImageButton = findViewById(R.id.forgot_activity_home)
        buttonHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}