package com.imit.smallMatfak.ui.screens.abouttask.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.Answer
import com.imit.smallMatfak.model.Task
import com.imit.smallMatfak.ui.screens.abouttask.presenter.AboutTaskStudentPresenter
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.listtasks.student.view.ListTasksStudentActivity
import com.imit.smallMatfak.ui.screens.solve.view.SolveTaskStudentActivity

class AboutTaskStudentActivity: BaseActivity(), AboutTaskStudentView{
    @BindView(R.id.about_task_student_cross) lateinit var buttonCross: ImageButton
    @BindView(R.id.about_task_student_back) lateinit var buttonBack: ImageButton
    @BindView(R.id.about_task_student_mark) lateinit var imageMark: ImageView
    @BindView(R.id.about_task_student_mark_text) lateinit var textMark: TextView
    @BindView(R.id.about_task_student_condition_text) lateinit var textCondition: TextView
    @BindView(R.id.about_task_student_solve) lateinit var buttonSolve: ImageButton

    private var idTask: Int = -1
    private var idAnswer: Int = -1
    private lateinit var token: String
    private lateinit var presenter: AboutTaskStudentPresenter<AboutTaskStudentView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_task_student)
        ButterKnife.bind(this)

        token = intent.getStringExtra("token") ?: ""
        idTask = intent.getIntExtra("idTask", idTask)
        idAnswer =  intent.getIntExtra("idAnswer", idAnswer)

        presenter = AboutTaskStudentPresenter()
        presenter.onAttach(this)
        presenter.changeInfoAboutTask(idTask, idAnswer)
        setOnClickListener()
    }

    override fun openAreaStudentActivity() {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun openListTaskStudentActivity() {
        val intent = Intent(this,ListTasksStudentActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("backListTasks", true)
        startActivity(intent)
    }

    override fun openSolveTaskActivity() {
        val intent = Intent(this, SolveTaskStudentActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("idTask", idTask)
        intent.putExtra("idAnswer", idAnswer)
        startActivity(intent)
    }

    override fun changeInfoAboutTask(task: Task, answer: Answer) {
        if(answer.isCorrect){
            imageMark.setBackgroundResource(R.drawable.green_mark)
            textMark.text = getString(R.string.solved_green)
        } else {
            imageMark.setBackgroundResource(R.drawable.red_mark)
            textMark.text = getString(R.string.solved_red)
        }

        textCondition.text = task.condition
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setOnClickListener(){
        buttonBack.setOnClickListener { presenter.openListTasks() }
        buttonCross.setOnClickListener { presenter.backArea() }
        buttonSolve.setOnClickListener { presenter.openSolveTask() }
    }
}