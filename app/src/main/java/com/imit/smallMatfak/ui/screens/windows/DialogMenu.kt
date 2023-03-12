package com.imit.smallMatfak.ui.screens.windows

import android.app.Dialog
import android.content.Context
import android.widget.ImageButton
import android.widget.ListView
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.R
import com.imit.smallMatfak.ui.adapter.ListAdapter
import com.imit.smallMatfak.ui.screens.area.student.presenter.PersonalAreaStudentPresenter
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentView
import com.imit.smallMatfak.ui.screens.area.teacher.presenter.PersonalAreaTeacherPresenter

class DialogMenu(val context: Context, var presenter: IDialogUserPresenter) {
    private val dialogMenu = Dialog(context)
    private lateinit var changeListView: ListView
    private lateinit var chooseButton: ImageButton
    private lateinit var crossSettings: ImageButton

    private val itemsSettings: List<String> = listOf(context.resources.getString(R.string.change_password))
    private val itemsPlayStudent: List<String> = listOf(context.resources.getString(R.string.login_room), context.resources.getString(R.string.solved_tasks))
    private val itemsPlayTeacher: List<String> = listOf(context.resources.getString(R.string.create_room), context.resources.getString(R.string.statistics))
    private val itemsTasksButton: List<String> = listOf(context.resources.getString(R.string.add_task), context.resources.getString(R.string.list_tasks))

    fun showDialogMenu(typeDialogMenu: TypeDialogMenu) {
        val itemsMenu = when (typeDialogMenu) {
            TypeDialogMenu.SETTINGS -> itemsSettings
            TypeDialogMenu.PLAY_STUDENT -> itemsPlayStudent
            TypeDialogMenu.PLAY_TEACHER -> itemsPlayTeacher
            TypeDialogMenu.TASKS -> itemsTasksButton
        }

        dialogMenu.setContentView(R.layout.window_menu)
        DialogUtils.settingsDialog(dialogMenu)
        initial(dialogMenu)

        val adapterListString = ListAdapter(context, R.layout.menu_list_item, itemsMenu)
        changeListView.adapter = adapterListString

        changeListView.setOnItemClickListener { _, _, pos, _ ->
            adapterListString.setSelectedPosition(pos)
            adapterListString.notifyDataSetChanged()
        }

        setOnClick(adapterListString, typeDialogMenu, itemsMenu)
        DialogUtils.dismissDialogWindow(dialogMenu, crossSettings)
    }

    private fun setOnClick(
        adapterListString: ListAdapter, typeDialogMenu: TypeDialogMenu,
        itemsMenu: List<String>
    ) {
        chooseButton.setOnClickListener {
            val itemSelected = adapterListString.getSelectedString()
            when (typeDialogMenu) {
                TypeDialogMenu.SETTINGS -> {
                    if (itemSelected == itemsMenu[0]) {
                        presenter.showDialogChangePassword()
                        dialogMenu.dismiss()
                    }
                }

                TypeDialogMenu.PLAY_STUDENT -> {
                    if (itemSelected == itemsMenu[0]) {
                        (presenter as PersonalAreaStudentPresenter<*>).showDialogWriteCodeRoom()
                        dialogMenu.dismiss()
                    }
                }

                TypeDialogMenu.PLAY_TEACHER -> {}

                TypeDialogMenu.TASKS -> {}
            }
        }
    }

    private fun initial(dialog: Dialog){
        changeListView = dialog.findViewById(R.id.menu_list)
        chooseButton = dialog.findViewById(R.id.menu_choose)
        crossSettings = dialog.findViewById(R.id.menu_cross)
    }
}