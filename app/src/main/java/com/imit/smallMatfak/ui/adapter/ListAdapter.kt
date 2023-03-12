package com.imit.smallMatfak.ui.adapter

import com.imit.smallMatfak.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ListAdapter(context: Context, private val resource: Int, private val list: List<String>):
    ArrayAdapter<String>(context, resource, list) {

    private var selectedPosition = 0

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): String {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView: TextView
        if(convertView == null){
            val view = LayoutInflater.from(context).inflate(resource, null)
            textView = view.findViewById(R.id.settings_list_item_not_selected)

        } else {
            textView = convertView as TextView
        }
        val textItem: String = getItem(position)
        textView.text = textItem

        if(selectedPosition == position){
            textView.setPadding(20, 0, 0, 0)
            textView.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.small_arrow_menu, 0, 0, 0
            )
        } else {
            textView.setPadding(170, 0, 0, 0)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0
            )
        }

        return  textView
    }

    fun setSelectedPosition(position: Int){
        selectedPosition = position
    }

    fun getSelectedString(): String{
        return getItem(selectedPosition)
    }
}