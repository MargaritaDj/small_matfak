package com.imit.smallMatfak.ui.screens.listtasks.student.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import butterknife.BindView
import butterknife.ButterKnife
import com.imit.smallMatfak.R
import com.imit.smallMatfak.model.TaskAnswerStudent
import com.imit.smallMatfak.ui.adapter.ListTasksStudentAdapter
import com.imit.smallMatfak.ui.paginator.Paginator
import com.imit.smallMatfak.ui.screens.abouttask.view.AboutTaskStudentActivity
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.listtasks.student.presenter.ListTasksStudentPresenter

class ListTasksStudentActivity: BaseActivity(), ListTasksStudentView {
    @BindView(R.id.list_tasks_student_cross) lateinit var buttonCross: ImageButton
    @BindView(R.id.list_tasks_student_back) lateinit var buttonBack: ImageButton
    @BindView(R.id.list_tasks_student_list) lateinit var listTasks: ListView
    @BindView(R.id.list_tasks_student_arrow_left) lateinit var arrowLeft: ImageButton
    @BindView(R.id.list_tasks_student_arrow_right) lateinit var arrowRight: ImageButton

    private val alpha = 0.4F
    private var page = 1
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapterListTasksAnswer: ListTasksStudentAdapter
    private lateinit var presenter: ListTasksStudentPresenter<ListTasksStudentView>
    private lateinit var token: String
    private lateinit var map:  MutableMap<String, List<TaskAnswerStudent>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tasks_student)
        ButterKnife.bind(this)
        sharedPreferences = getSharedPreferences("APP_SHARED_PREF", Context.MODE_PRIVATE)

        presenter = ListTasksStudentPresenter(sharedPreferences)
        presenter.onAttach(this)

        token = intent.getStringExtra("token") ?: ""
        map = presenter.getTasksAnswerStudent(token)

        setOnClickListener()

        page = getPage()
        if(page != 0 && intent.getBooleanExtra("backListTasks", false)){
            arrowRight.callOnClick()
        } else{
            page = 1
            removeInfoAboutPage()
            arrowLeft.isEnabled = false
            arrowLeft.alpha = alpha
        }

       adapterListTasksAnswer = ListTasksStudentAdapter(this, R.layout.tasks_list_item_task,
           Paginator.generatePageTasksStudent(map, page), Paginator.newPageStartTask, page, presenter)
        listTasks.adapter = adapterListTasksAnswer
    }

    override fun showTasksRight(map: MutableMap<String, List<TaskAnswerStudent>>, maxPage: Int){
        if(page != maxPage){
            page++
            adapterListTasksAnswer = ListTasksStudentAdapter(this, R.layout.tasks_list_item_task,
                Paginator.generatePageTasksStudent(map, page), Paginator.newPageStartTask, page, presenter)
            listTasks.adapter = adapterListTasksAnswer
            arrowLeft.alpha = 1F
            arrowLeft.isEnabled = true
        }

        if(page == maxPage){
            arrowRight.alpha = alpha
            arrowRight.isEnabled = false
        }
    }

    override fun showTasksLeft(map: MutableMap<String, List<TaskAnswerStudent>>){
        if(page != 1){
            page--
            adapterListTasksAnswer = ListTasksStudentAdapter(this, R.layout.tasks_list_item_task,
                Paginator.generatePageTasksStudent(map, page), Paginator.newPageStartTask, page, presenter)
            listTasks.adapter = adapterListTasksAnswer
            arrowRight.alpha = 1F
            arrowRight.isEnabled = true
        }

        if(page == 1){
            arrowLeft.alpha = alpha
            arrowLeft.isEnabled = false
        }
    }

    override fun openInfoTaskActivity(idTask: Int, idAnswer: Int) {
        val intent = Intent(this, AboutTaskStudentActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("idTask", idTask )
        intent.putExtra("idAnswer", idAnswer)
        startActivity(intent)
    }

    override fun openAreaStudentActivity() {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun openMenu() {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        intent.putExtra("clickPlay", true)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun setOnClickListener(){
        arrowLeft.setOnClickListener { presenter.showTasksLeft(map) }
        arrowRight.setOnClickListener { presenter.showTasksRight(map) }
        buttonBack.setOnClickListener { presenter.backMenu() }
        buttonCross.setOnClickListener { presenter.backArea() }
    }

    private fun getPage(): Int{
       return sharedPreferences.getInt("pageListTasks", 1)
    }

    private fun removeInfoAboutPage(){
        val editorSharedPreferences = sharedPreferences.edit()
        editorSharedPreferences.remove("pageListTasks")
        editorSharedPreferences.apply()
    }
}