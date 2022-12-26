package com.imit.smallMatfak.screens

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R

class NewPasswordActivity : AppCompatActivity(){
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
    }
}