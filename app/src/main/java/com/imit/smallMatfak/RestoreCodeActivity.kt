package com.imit.smallMatfak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RestoreCodeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_code)

        val buttonChange: Button = findViewById(R.id.restore_activity_change_button)
        buttonChange.setOnClickListener{
            val intent = Intent(this, NewPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}