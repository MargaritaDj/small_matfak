package com.imit.smallMatfak.ui.screens.windows

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageButton

object DialogUtils {
    fun settingsDialog(dialog: Dialog){
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

   fun dismissDialogWindow(dialog: Dialog, cross: ImageButton) {
        cross.setOnClickListener {
            dialog.dismiss()
        }
    }
}