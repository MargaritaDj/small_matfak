package com.imit.smallMatfak.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import com.imit.smallMatfak.R

object UtilsView {

    fun startPhone(editText: EditText) {
        val startPhone = "+7"
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (editText.text.toString() == "" && hasFocus) {
                editText.append(startPhone)
            }
        }
        editText.addTextChangedListener {
            if (editText.text.toString().length < 2 ||
                !editText.text.toString().startsWith(startPhone)
            ) {
                editText.setText(startPhone)
                editText.setSelection(editText.text.toString().length)
            }
        }
    }

    fun changePasswordVisibility(editText: EditText, button: ImageButton) {
        button.setOnClickListener {
            if (button.tag == "1") {
                button.setBackgroundResource(R.drawable.icon_open_eye)
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                button.tag = "2"
            } else {
                button.setBackgroundResource(R.drawable.icon_close_eye)
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
                button.tag = "1"
            }
            editText.setSelection(editText.text.toString().length)
        }
    }

    fun switchCellsCode(
        editText1: EditText, editText2: EditText,
        editText3: EditText, editText4: EditText
    ) {
        editText1.addTextChangedListener {
            if (editText1.text.toString() != "") {
                editText2.requestFocus()
            }
        }
        editText2.addTextChangedListener {
            if (editText2.text.toString() != "") {
                editText3.requestFocus()
            }
        }
        editText3.addTextChangedListener {
            if (editText3.text.toString() != "") {
                editText4.requestFocus()
            }
        }
    }
}