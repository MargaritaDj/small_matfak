package com.imit.smallMatfak.ui.screens.windows

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.MotionEvent
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.R
import com.imit.smallMatfak.ui.adapter.ImageAdapter
import com.imit.smallMatfak.ui.paginator.Paginator
import com.imit.smallMatfak.ui.screens.area.student.presenter.PersonalAreaStudentPresenter

class DialogChoiceHero(val context: Context, val presenter: IDialogStudentPresenter) {
    private lateinit var gridHeroes: GridView
    private lateinit var buttonChoice: ImageButton
    private lateinit var buttonArrowLeft: ImageButton
    private lateinit var buttonArrowRight: ImageButton
    private lateinit var crossButton: ImageButton

    private val dialogChoiceHero = Dialog(context)
    private val arrayHeroes = arrayListOf(
        R.drawable.hero_feiry, R.drawable.hero_orc, R.drawable.hero_feiry,
        R.drawable.hero_feiry, R.drawable.hero_feiry, R.drawable.hero_feiry, R.drawable.hero_feiry,
        R.drawable.hero_feiry, R.drawable.hero_feiry, R.drawable.hero_feiry
    )
    private val alpha = 0.4F
    private var page = 1
    private lateinit var adapterImage: ImageAdapter

    @SuppressLint("ClickableViewAccessibility")
    fun showDialogChoiceHero() {
        dialogChoiceHero.setContentView(R.layout.window_choice_hero)
        DialogUtils.settingsDialog(dialogChoiceHero)
        initial(dialogChoiceHero)

        buttonArrowLeft.isEnabled = false
        buttonArrowLeft.alpha = alpha

        adapterImage = ImageAdapter(context = context, context.resources,
            Paginator.generatePageGridViewHero(arrayHeroes, page))
        gridHeroes.adapter = adapterImage

        gridHeroes.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ ->
            adapterImage.setSelectedPosition(pos)
            adapterImage.notifyDataSetChanged()
        }

        gridHeroes.setOnTouchListener { _, event ->
            event.action == MotionEvent.ACTION_MOVE
        }

        setOnClickListener()
        DialogUtils.dismissDialogWindow(dialogChoiceHero, crossButton)
    }

    fun showHeroesRight(maxPage: Int){
        if(page != maxPage){
            page++
            adapterImage = ImageAdapter(context = context, context.resources,
                Paginator.generatePageGridViewHero(arrayHeroes, page))
            gridHeroes.adapter = adapterImage
            buttonArrowLeft.alpha = 1F
            buttonArrowLeft.isEnabled = true
        }

        if(page == maxPage){
            buttonArrowRight.alpha = alpha
            buttonArrowRight.isEnabled = false
        }
    }

    fun showHeroesLeft(){
        if(page != 1){
            page--
            adapterImage = ImageAdapter(context = context, context.resources,
                Paginator.generatePageGridViewHero(arrayHeroes, page))
            gridHeroes.adapter = adapterImage
            buttonArrowRight.alpha = 1F
            buttonArrowRight.isEnabled = true
        }

        if(page == 1){
            buttonArrowLeft.alpha = alpha
            buttonArrowLeft.isEnabled = false
        }
    }

    private fun setOnClickListener(){
        buttonArrowLeft.setOnClickListener { presenter.showHeroesLeft(this) }
        buttonArrowRight.setOnClickListener { presenter.showHeroesRight(this, arrayHeroes)}

        buttonChoice.setOnClickListener {
            val imageHero: Int = adapterImage.getSelectedImageHero() as Int
            presenter.changeImageHero(imageHero, this)
            dialogChoiceHero.dismiss()
        }
    }

    private fun initial(dialog: Dialog){
        gridHeroes = dialog.findViewById(R.id.choice_hero_grid)
        buttonChoice = dialog.findViewById(R.id.choice_hero_button)
        buttonArrowLeft = dialog.findViewById(R.id.choice_hero_arrow_left)
        buttonArrowRight = dialog.findViewById(R.id.choice_hero_arrow_right)
        crossButton = dialog.findViewById(R.id.choice_hero_cross)
    }
}