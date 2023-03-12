package com.imit.smallMatfak.ui.screens.windows

import android.app.Dialog
import android.content.Context
import android.widget.ImageButton
import com.imit.smallMatfak.R

class DialogCodeRoom(val context: Context, val presenter: IDialogUserPresenter) {
    private val dialogCodeRoom = Dialog(context)

    fun showDialogWriteCodeRoom(){
        dialogCodeRoom.setContentView(R.layout.window_code_room)
        DialogUtils.settingsDialog(dialogCodeRoom)

        val arrowBack: ImageButton = dialogCodeRoom.findViewById(R.id.code_room_back)
        val cross: ImageButton = dialogCodeRoom.findViewById(R.id.code_room_cross)

        arrowBack.setOnClickListener {
            presenter.showDialogMenu(TypeDialogMenu.PLAY_STUDENT)
            dialogCodeRoom.dismiss()
        }
        DialogUtils.dismissDialogWindow(dialogCodeRoom, cross)
    }
}