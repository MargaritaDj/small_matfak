package com.imit.smallMatfak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val buttonGetCode: Button = findViewById(R.id.forgot_activity_get_button)
        buttonGetCode.setOnClickListener {
            val intent = Intent(this, RestoreCodeActivity::class.java)
            startActivity(intent)
        }
    }
}