package com.imit.smallMatfak.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.ui.screens.listtasks.student.presenter.ListTasksStudentPresenter
import com.imit.smallMatfak.ui.screens.listtasks.student.view.ListTasksStudentView
import java.util.Objects

class ListTasksStudentAdapter(
    context: Context, private val resourceTask: Int,
    pairDataList: Pair<String, List<TaskAnswerStudent>>, private val taskIsFirst: Int, val page: Int,
    private val presenter: ListTasksStudentPresenter<ListTasksStudentView>
) : ArrayAdapter<TaskAnswerStudent>(context, resourceTask, pairDataList.second) {
    private val date = pairDataList.first
    private val list = pairDataList.second

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): TaskAnswerStudent {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textViewTask: TextView
        val textViewDate: TextView
        val button: ImageButton
        val mark: ImageView
        val viewTask = convertView ?: LayoutInflater.from(context).inflate(resourceTask, null)

        textViewTask = viewTask.findViewById(R.id.task_item_text)
        textViewDate = viewTask.findViewById(R.id.task_item_date)
        button = viewTask.findViewById(R.id.task_item_open_button)
        mark = viewTask.findViewById(R.id.task_item_mark)

        if(date != "" && taskIsFirst == position){
            textViewDate.text = date
            textViewDate.height = 30 * context.resources.displayMetrics.density.toInt()
            textViewDate.setPadding(0, 5, 0, 0)
        } else {
            textViewDate.text = ""
            if(position == 0){
                textViewDate.height = 30 * context.resources.displayMetrics.density.toInt()
            } else {
                textViewDate.height = 10 * context.resources.displayMetrics.density.toInt()
            }
            textViewDate.setPadding(0, 0, 0, 0)
        }

        textViewTask.text = getItem(position).condition
        if(!getItem(position).isCorrect){
            mark.setImageResource(R.drawable.red_mark)
        } else {
            mark.setImageResource(R.drawable.green_mark)
        }

        button.setOnClickListener {
            presenter.openInfoTask(getItem(position).idTask,getItem(position).idAnswer, page)
        }

        return viewTask
    }

}