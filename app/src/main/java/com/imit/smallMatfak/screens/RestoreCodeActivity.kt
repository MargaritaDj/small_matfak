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

class RestoreCodeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_code)

        val editTextCode1: EditText = findViewById(R.id.restore_activity_cell_code1_text)
        val editTextCode2: EditText = findViewById(R.id.restore_activity_cell_code2_text)
        val editTextCode3: EditText = findViewById(R.id.restore_activity_cell_code3_text)
        val editTextCode4: EditText = findViewById(R.id.restore_activity_cell_code4_text)

        val layoutCode1: TextInputLayout = findViewById(R.id.restore_activity_cell_code1)
        val layoutCode2: TextInputLayout = findViewById(R.id.restore_activity_cell_code2)
        val layoutCode3: TextInputLayout = findViewById(R.id.restore_activity_cell_code3)
        val layoutCode4: TextInputLayout = findViewById(R.id.restore_activity_cell_code4)

        UtilsView.switchCellsCode(editTextCode1, editTextCode2, editTextCode3, editTextCode4,
            layoutCode1, layoutCode2, layoutCode3, layoutCode4)

        val buttonChange: Button = findViewById(R.id.restore_activity_change_button)
        buttonChange.setOnClickListener {
            val isCorrect1 = Validator.validationCell(layoutCode1, editTextCode1)
            val isCorrect2 = Validator.validationCell(layoutCode2, editTextCode2)
            val isCorrect3 = Validator.validationCell(layoutCode3, editTextCode3)
            val isCorrect4 = Validator.validationCell(layoutCode4, editTextCode4)

            if (isCorrect1 && isCorrect2 && isCorrect3 && isCorrect4) {
                startActivity(Intent(this, NewPasswordActivity::class.java))
            }
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