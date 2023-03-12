package com.imit.smallMatfak.ui.screens.windows

import android.app.Dialog
import android.content.Context
import android.widget.ImageButton
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.R
import java.io.BufferedReader

class DialogRules(val context: Context, val presenter: IDialogUserPresenter) {
    private lateinit var rulesText: TextView
    private lateinit var arrowLeft: ImageButton
    private lateinit var arrowRight: ImageButton
    private lateinit var crossButton: ImageButton

    private val dialogRules = Dialog(context)
    private lateinit var stringPages: List<String>
    private val alpha = 0.4F
    private var page = 1

    fun showDialogRules(bufferedReader: BufferedReader) {
        dialogRules.setContentView(R.layout.window_rules)
        DialogUtils.settingsDialog(dialogRules)
        initial(dialogRules)

        DialogUtils.dismissDialogWindow(dialogRules, crossButton)
        stringPages = presenter.getRulesPage(bufferedReader)

        rulesText.text = stringPages[page - 1]
        arrowLeft.alpha = alpha
        arrowLeft.isEnabled = false

        arrowRight.setOnClickListener { presenter.showRulesRight(this) }
        arrowLeft.setOnClickListener { presenter.showRulesLeft(this) }
    }

    fun showRulesRight(){
        if (page != stringPages.size) {
            page++
            rulesText.text = stringPages[page - 1].trim()
            arrowLeft.alpha = 1F
            arrowLeft.isEnabled = true
        }

        if (page == stringPages.size) {
            arrowRight.alpha = alpha
            arrowRight.isEnabled = false
        }
    }

    fun showRulesLeft(){
        if (page != 1) {
            page--
            rulesText.text = stringPages[page - 1].trim()
            arrowRight.alpha = 1F
            arrowRight.isEnabled = true
        }

        if (page == 1) {
            arrowLeft.alpha = alpha
            arrowLeft.isEnabled = false
        }
    }

    private fun initial(dialog: Dialog){
        rulesText = dialog.findViewById(R.id.rules_text)
        arrowLeft = dialog.findViewById(R.id.rules_arrow_left)
        arrowRight = dialog.findViewById(R.id.rules_arrow_right)
        crossButton = dialog.findViewById(R.id.rules_cross)
    }
}