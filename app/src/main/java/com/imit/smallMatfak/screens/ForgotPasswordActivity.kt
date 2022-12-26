package com.imit.smallMatfak.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R

class ForgotPasswordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val buttonGetCode: Button = findViewById(R.id.forgot_activity_get_button)
        buttonGetCode.setOnClickListener {
            startActivity(Intent(this, RestoreCodeActivity::class.java))
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