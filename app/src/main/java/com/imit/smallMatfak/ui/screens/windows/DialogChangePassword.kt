package com.imit.smallMatfak.ui.screens.windows

import android.app.Dialog
import android.content.Context
import android.widget.EditText
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.R
import com.imit.smallMatfak.utils.UtilsView

class DialogChangePassword(val context: Context, val presenter: IDialogUserPresenter) {
    private val dialogSettings = Dialog(context)

    private lateinit var crossChange: ImageButton
    private lateinit var changeButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var oldPassword: EditText
    private lateinit var newPassword: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var oldPasswordLayout: TextInputLayout
    private lateinit var newPasswordLayout: TextInputLayout
    private lateinit var repeatPasswordLayout: TextInputLayout
    private lateinit var oldEye: ImageButton
    private lateinit var newEye: ImageButton
    private lateinit var repeatEye: ImageButton

    fun showDialogChangePassword() {
        dialogSettings.setContentView(R.layout.window_change_password)
        DialogUtils.settingsDialog(dialogSettings)
        initial(dialogSettings)
        utils()
        onClickListener()
        DialogUtils.dismissDialogWindow(dialogSettings, crossChange)
    }

    private fun utils(){
        UtilsView.removeErrorOnFocus(oldPassword, oldPasswordLayout)
        UtilsView.removeErrorOnFocus(newPassword, newPasswordLayout)
        UtilsView.removeErrorOnFocus(repeatPassword, repeatPasswordLayout)

        UtilsView.changePasswordVisibility(oldPassword, oldEye)
        UtilsView.changePasswordVisibility(newPassword, newEye)
        UtilsView.changePasswordVisibility(repeatPassword, repeatEye)
    }

    private fun onClickListener(){
        changeButton.setOnClickListener {
            presenter.changePassword(oldPassword.text.toString(), newPassword.text.toString(),
                repeatPassword.text.toString(), this)
           // dialogSettings.dismiss()
        }
        backButton.setOnClickListener {
            presenter.showDialogMenu(TypeDialogMenu.SETTINGS)
            dialogSettings.dismiss()
        }
    }

    fun showValidationErrorOldPassword(message: String){
        oldPasswordLayout.error = message
    }

    fun showValidationErrorNewPassword(message: String){
        newPasswordLayout.error = message
    }

    fun showValidationErrorRepeatPassword(message: String){
        repeatPasswordLayout.error = message
    }

    fun dismiss(){
        dialogSettings.dismiss()
    }

    private fun initial(dialog: Dialog){
        crossChange = dialog.findViewById(R.id.change_password_cross)
        changeButton = dialog.findViewById(R.id.change_password_button)
        backButton = dialog.findViewById(R.id.change_password_back)
        oldPassword = dialog.findViewById(R.id.change_password_old_edit_text)
        newPassword = dialog.findViewById(R.id.change_password_new_edit_text)
        repeatPassword = dialog.findViewById(R.id.change_password_repeat_edit_text)
        oldPasswordLayout = dialog.findViewById(R.id.change_password_old)
        newPasswordLayout = dialog.findViewById(R.id.change_password_new)
        repeatPasswordLayout = dialog.findViewById(R.id.change_password_repeat)
        oldEye = dialog.findViewById(R.id.change_password_eye1)
        newEye = dialog.findViewById(R.id.change_password_eye2)
        repeatEye = dialog.findViewById(R.id.change_password_eye3)
    }
}