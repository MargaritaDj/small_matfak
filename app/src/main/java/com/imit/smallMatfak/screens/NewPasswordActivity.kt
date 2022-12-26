package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.utils.UtilsView

class NewPasswordActivity : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        val buttonBack: ImageButton = findViewById(R.id.new_password_activity_activity_back)
        buttonBack.setOnClickListener {
            startActivity(Intent(this, RestoreCodeActivity::class.java))
        }

        val buttonHome: ImageButton = findViewById(R.id.new_password_activity_home)
        buttonHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val editTextPassword: EditText = findViewById(R.id.new_password_activity_password)
        val buttonPassword1: ImageButton = findViewById(R.id.new_password_button_eye1)
        UtilsView.changePasswordVisibility(editTextPassword, buttonPassword1)

        val editTextPasswordRepeat: EditText = findViewById(R.id.new_password_activity_repeat_password)
        val buttonPassword2: ImageButton = findViewById(R.id.new_password_button_eye2)
        UtilsView.changePasswordVisibility(editTextPasswordRepeat, buttonPassword2)
    }
}