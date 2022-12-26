package com.imit.smallMatfak.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R

class RestoreCodeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_code)

        val buttonChange: Button = findViewById(R.id.restore_activity_change_button)
        buttonChange.setOnClickListener{
            startActivity(Intent(this, NewPasswordActivity::class.java))
        }

        val buttonBack: ImageButton = findViewById(R.id.restore_activity_back)
        buttonBack.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        val buttonHome: ImageButton = findViewById(R.id.restore_activity_home)
        buttonHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}