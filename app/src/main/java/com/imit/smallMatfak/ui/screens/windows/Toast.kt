package com.imit.smallMatfak.ui.screens.windows

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.imit.smallMatfak.R

object Toast {
    fun showToastOk(layout: View, text: String, context: Context) {
        val textToast: TextView = layout.findViewById(R.id.custom_toast_text)
        textToast.text = text
        layout.setBackgroundResource(R.drawable.toast_ok)
        with(Toast(context)){
            setGravity(Gravity.BOTTOM, 0, 100)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    fun showToastError(layout: View, text: String, context: Context){
        val textToast: TextView = layout.findViewById(R.id.custom_toast_text)
        textToast.text = text
        layout.setBackgroundResource(R.drawable.toast_error)
        with(Toast(context)){
            setGravity(Gravity.BOTTOM, 0, 100)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }
}