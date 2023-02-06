package com.imit.smallMatfak.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.imit.smallMatfak.MainActivity
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.User

class PersonalAreaStudentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area_student)

        val buttonLogout: ImageButton = findViewById(R.id.personal_area_student_logout)
        buttonLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val nameTextView: TextView = findViewById(R.id.personal_area_student_name)
        val lastNameTextView: TextView = findViewById(R.id.personal_area_student_last_name)

        val user = intent.getSerializableExtra("user") as? User

        nameTextView.text = user?.firstName
        lastNameTextView.text = user?.lastName

        val settingsMenuView: ImageButton = findViewById(R.id.personal_area_student_settings)
        settingsMenuView.setOnClickListener {
            showPopupSettings(this, settingsMenuView)
        }
    }
}

fun showPopupSettings(context: Context, settingsMenuView: View){
    val popup = PopupMenu(context, settingsMenuView)
    val inflater: MenuInflater = popup.menuInflater
    inflater.inflate(R.menu.popup_settings, popup.menu)
    popup.setOnMenuItemClickListener { menuItem ->
        when (menuItem.itemId) {
            R.id.menu_change_password -> {

            }
        }
        true
    }
    popup.show()
}
