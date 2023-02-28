package com.imit.smallMatfak.utils

import android.graphics.Color
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
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
                button.setBackgroundResource(R.drawable.icon_close_eye)
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                button.tag = "2"
            } else {
                button.setBackgroundResource(R.drawable.icon_open_eye)
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
                button.tag = "1"
            }
            editText.setSelection(editText.text.toString().length)
        }
    }

    fun switchCellsCode(
        editText1: EditText, editText2: EditText,
        editText3: EditText, editText4: EditText,
        layoutText1: TextInputLayout, layoutText2: TextInputLayout,
        layoutText3: TextInputLayout, layoutText4: TextInputLayout
    ) {
        editText1.addTextChangedListener {
            layoutText1.error = ""
            if (editText1.text.toString() != "") {
                editText2.requestFocus()
            }
        }
        editText2.addTextChangedListener {
            layoutText2.error = ""
            if (editText2.text.toString() != "") {
                editText3.requestFocus()
            }
        }
        editText3.addTextChangedListener {
            layoutText3.error = ""
            if (editText3.text.toString() != "") {
                editText4.requestFocus()
            }
        }
        editText4.addTextChangedListener {
            layoutText4.error = ""
        }
    }

   /* fun onFocusCell(editText: EditText){
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
               editText.setText("")
            }
        }
    }*/

    fun removeErrorOnFocus(editText: EditText, layoutText: TextInputLayout){
        editText.addTextChangedListener {
                layoutText.error = ""
        }
    }
}