package com.imit.smallMatfak.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.utils.UtilsView

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

        val editTextCode1: EditText = findViewById(R.id.restore_activity_cell_code1)
        val editTextCode2: EditText = findViewById(R.id.restore_activity_cell_code2)
        val editTextCode3: EditText = findViewById(R.id.restore_activity_cell_code3)
        val editTextCode4: EditText = findViewById(R.id.restore_activity_cell_code4)

        UtilsView.switchCellsCode(editTextCode1, editTextCode2, editTextCode3, editTextCode4)
    }
}