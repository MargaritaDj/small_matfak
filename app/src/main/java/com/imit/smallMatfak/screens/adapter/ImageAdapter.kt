package com.imit.smallMatfak.screens.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import com.imit.smallMatfak.R

class ImageAdapter(val context: Context, private val resources: Resources) : BaseAdapter() {
    private val arrayHeroes = arrayListOf(
        R.drawable.feiry, R.drawable.home, R.drawable.feiry,
        R.drawable.feiry, R.drawable.feiry, R.drawable.feiry, R.drawable.feiry,
        R.drawable.feiry
    )

    private var selectedPosition = 0

    override fun getCount(): Int {
        return arrayHeroes.size
    }

    override fun getItem(position: Int): Any {
        return arrayHeroes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.CENTER
            val pxFromDp70: Int = 70 * context.resources.displayMetrics.density.toInt()
            imageView.layoutParams = AbsListView.LayoutParams(pxFromDp70, pxFromDp70)
        } else {
            imageView = convertView as ImageView
        }
        val heroImageInt = getItem(position) as Int
        val bitMap: Bitmap = BitmapFactory.decodeResource(resources, heroImageInt)
        val pxFromDp65: Int = 65 * context.resources.displayMetrics.density.toInt()
        val bitMapIcon = Bitmap.createScaledBitmap(bitMap, pxFromDp65, pxFromDp65, false)
        imageView.setImageBitmap(bitMapIcon)

        if(selectedPosition == position){
            imageView.background = ResourcesCompat.getDrawable(resources, R.drawable.frame_hero, null)
        } else {
            imageView.background = ColorDrawable(Color.TRANSPARENT)
        }
        return imageView
    }

    fun setSelectedPosition(position: Int){
        selectedPosition = position
    }

    fun getSelectedImageHero(): Any{
        return getItem(selectedPosition)
    }
}