package com.imit.smallMatfak.ui.screens.solve.view

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
import com.imit.smallMatfak.ui.screens.abouttask.view.AboutTaskStudentActivity
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.solve.presenter.SolveTaskStudentPresenter

class SolveTaskStudentActivity: BaseActivity(), SolveTaskStudentView {
    @BindView(R.id.solve_task_student_cross) lateinit var buttonCross: ImageButton
    @BindView(R.id.solve_task_student_back) lateinit var buttonBack: ImageButton
    @BindView(R.id.solve_task_student_solve_text) lateinit var solveTest: TextView
    @BindView(R.id.solve_task_student_right_answer) lateinit var rightAnswer: TextView
    @BindView(R.id.solve_task_student_answer) lateinit var studentAnswer: TextView

    private var idTask: Int = -1
    private var idAnswer: Int = -1
    private lateinit var token: String
    private lateinit var presenter: SolveTaskStudentPresenter<SolveTaskStudentView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve_task_student)
        ButterKnife.bind(this)

        token = intent.getStringExtra("token") ?: ""
        idTask = intent.getIntExtra("idTask", idTask)
        idAnswer =  intent.getIntExtra("idAnswer", idAnswer)

        presenter = SolveTaskStudentPresenter()
        presenter.onAttach(this)
        presenter.changeInfoAboutSolve(idTask, idAnswer)

        buttonBack.setOnClickListener { presenter.openAboutTask() }
        buttonCross.setOnClickListener { presenter.backArea() }
    }

    override fun openAboutTaskActivity() {
        val intent = Intent(this, AboutTaskStudentActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("idTask", idTask)
        intent.putExtra("idAnswer", idAnswer)
        startActivity(intent)
    }

    override fun openAreaStudentActivity() {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun changeInfoAboutSolve(task: Task, answer: Answer) {
        if(task.solve.isEmpty()){ solveTest.text = getString(R.string.not_solve) }
        else { solveTest.text = task.solve }
        rightAnswer.text = String.format(getString(R.string.right_answer) + " " + task.correctAnswer)
        studentAnswer.text = String.format(getString(R.string.student_answer) + " " + answer.answer)
    }
}