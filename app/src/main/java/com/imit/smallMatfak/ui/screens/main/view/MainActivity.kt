package com.imit.smallMatfak.ui.screens.main.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.imit.smallMatfak.R
import com.imit.smallMatfak.database.Database
import com.imit.smallMatfak.model.*
import com.imit.smallMatfak.ui.screens.forgotpassword.view.ForgotPasswordActivity
import com.imit.smallMatfak.ui.screens.area.student.view.PersonalAreaStudentActivity
import com.imit.smallMatfak.ui.screens.area.teacher.view.PersonalAreaTeacherActivity
import com.imit.smallMatfak.ui.screens.base.view.BaseActivity
import com.imit.smallMatfak.ui.screens.main.presenter.MainPresenter
import com.imit.smallMatfak.utils.UtilsView

class MainActivity : BaseActivity(), MainView {
    @BindView(R.id.main_activity_forgot_password) lateinit var buttonForgotPassword: Button
    @BindView(R.id.main_activity_button_eye) lateinit var buttonEye: ImageButton
    @BindView(R.id.main_activity_phone) lateinit var layoutLogin: TextInputLayout
    @BindView(R.id.main_activity_phone_text) lateinit var editTextLogin: EditText
    @BindView(R.id.main_activity_password) lateinit var layoutPassword: TextInputLayout
    @BindView(R.id.main_activity_password_text) lateinit var editTextPassword: EditText
    @BindView(R.id.main_activity_login_button) lateinit var buttonLogin: ImageButton

    private lateinit var presenter: MainPresenter<MainView>

    override fun onCreate(savedInstanceState: Bundle?) {
        addDatabase()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences("APP_SHARED_PREF",
            Context.MODE_PRIVATE)
        utilsTextView()
        onClickListener()
        presenter = MainPresenter(sharedPreferences)
        presenter.onAttach(this)
    }

    override fun showValidationMessageLogin(message: String) {
        layoutLogin.error = message
    }

    override fun showValidationMessagePassword(message: String) {
        layoutPassword.error = message
    }

    override fun openPersonalAreaStudentActivity(token: String) {
        val intent = Intent(this, PersonalAreaStudentActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun openPersonalAreaTeacherActivity(token: String) {
        val intent = Intent(this, PersonalAreaTeacherActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }

    override fun loginAuto(userLoginSharedPreferences: String, userPasswordSharedPreferences: String){
        editTextLogin.setText(userLoginSharedPreferences)
        editTextPassword.setText(userPasswordSharedPreferences)
        buttonLogin.callOnClick()
    }

    override fun openForgotPasswordActivity(){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun onClickListener(){
        buttonForgotPassword.setOnClickListener { presenter.forgotPassword() }
        buttonLogin.setOnClickListener { presenter.login(editTextLogin.text.toString(),
            editTextPassword.text.toString())}
    }


    private fun utilsTextView(){
        UtilsView.removeErrorOnFocus(editTextLogin, layoutLogin)
        UtilsView.startPhone(editTextLogin)
        UtilsView.removeErrorOnFocus(editTextPassword, layoutPassword)
        UtilsView.changePasswordVisibility(editTextPassword, buttonEye)
    }



    private fun addDatabase() {

        val student = Student(
            "Маргарита", "Джинджолия", "Сергеевна", R.drawable.hero_feiry,
            "9507999649", "123456", 11, 3, 19
        )
        val teacher = Teacher(
            "Ксения", "Филина", "Евгеньевна", R.drawable.hero_feiry,
            "9502181359", "654321"
        )

        Database.loginUsers.put(student.login, student)
        Database.loginUsers.put(teacher.login, teacher)

        val list1 = mutableListOf(
            TaskAnswerStudent(0,  "2 + 2 = ?", 0, false),
            TaskAnswerStudent(1, "В тёмной кладовой лежат ботинки одного размера: 10 пар чёрных и 10 пар коричневых. Найдите наименьшее число ботинок, которое нужно взять из кладовой, чтобы среди них оказалась хотя бы одна пара одного цвета (считать, что в темноте нельзя отличить не только цвет ботинка, но и левый от правого).",
                1, true),
            TaskAnswerStudent(2,   "Электропоезд длиной 18 м проехал мимо километрового столба за 9 с. Сколько времени понадобится ему, чтобы проехать мост длиной 36 м?",
                 2, false),
            TaskAnswerStudent(3,  "Трактор едет по дороге, проезжая 10 метров за каждую секунду. Выразите скорость трактора в километрах в час. В ответе укажите число.",
                3, true)
        )

        val list2 = mutableListOf(
            TaskAnswerStudent(4, "5 + 5 = ?", 4, true),
            TaskAnswerStudent(5, "5 + 2 = ?", 5, true),
            TaskAnswerStudent(6,  "15 - 2 = ?", 6,  true),
            TaskAnswerStudent(7,   "12 - 2 = ?", 7,  true),
            TaskAnswerStudent(8,  "1 + 1 = ?", 8, true),
        )

        val list3 = mutableListOf(
            TaskAnswerStudent(9, "Ежемесячная плата за телефон составляет 280 рублей в месяц. Сколько рублей составит ежемесячная плата за телефон, если она вырастет на 5%?",
                9, false),
            TaskAnswerStudent(10,  "Ваня потратил в компьютерном магазине 600 рублей. На покупку кабеля он израсходовал 15% этой суммы, а на покупку мыши— 30% этой суммы. Сколько рублей стоили остальные товары, купленные Ваней?",
                10, true),
        )

        val room1 = Room("1", "", "7", "12.03.2023", listOf())
        val room2 = Room("2", "", "7", "13.03.2023", listOf())
        val room3 = Room("3", "", "7", "14.03.2023", listOf())

        Database.studentRooms.put(student, listOf(room1, room2, room3))
        Database.answersStudentRoom.put(Pair(student, room1), list1)
        Database.answersStudentRoom.put(Pair(student, room2), list2)
        Database.answersStudentRoom.put(Pair(student, room3), list3)

        val task0 = Task(0, 7, "", "2 + 2 = ?", "","4")
        val task1 = Task(1, 7, "", "В тёмной кладовой лежат ботинки одного размера:" +
                " 10 пар чёрных и 10 пар коричневых. Найдите наименьшее число ботинок, которое нужно взять из кладовой," +
                " чтобы среди них оказалась хотя бы одна пара одного цвета (считать, что в темноте нельзя отличить не только " +
                "цвет ботинка, но и левый от правого).", "В худшем случае можно вытащить сперва 20 ботинков и все на одну" +
                " ногу. Тогда любой 21-ый ботинок составит пару ботинков одного цвета." ,"21")
        val task2 = Task(2, 7, "", "Электропоезд длиной 18 м проехал мимо километрового столба за " +
                "9 с. Сколько времени понадобится ему, чтобы проехать мост длиной 36 м?",
            "1) 18:9=2 (м/с) - скорость поезда\n" +
                    "2) Он проедет мост тогда, когда его последний вагон закончит движение по мосту, а это значит, что " +
                    "\"голова поезда\" удалится от моста на 18 метров.\n" +
                    "(36+18):2=27 (с)." ,"27")

        val task3 = Task(3, 7, "", "Трактор едет по дороге, проезжая 10 метров за каждую секунду. " +
                "Выразите скорость трактора в километрах в час. В ответе укажите число.",
            "Чтобы узнать ответ, нужно выяснить, сколько в часу секунд и в километрах метров. " +
                    "1 км=1000 м 1 час=3600 секунд Соответственно, 10 метров умножаем на 3600 секунд и делим на 1000 метров:" +
                    " 10*3600/1000=36 Ответ:36 км/ч." ,"36")

        val task4 = Task(4, 7, "", "5 + 5 = ?", "","10")
        val task5 = Task(5, 7, "", "5 + 2 = ?", "","7")
        val task6 = Task(6, 7, "", "15 - 2 = ?", "","13")
        val task7 = Task(7, 7, "", "12 - 2 = ?", "","10")
        val task8 = Task(8, 7, "", "1 + 1 = ?", "","2")

        val task9 = Task(9, 7, "", "Ежемесячная плата за телефон составляет 280 рублей в месяц. " +
                "Сколько рублей составит ежемесячная плата за телефон, если она вырастет на 5%?",
            "Для этого составим пропорцию: 280 рублей - 100%, х рублей - 5 %. Из этой пропорции можно выразить х: " +
                    "х= (280х5):100=15=14 (рублей). Теперь мы знаем, что при увеличении абонплаты за телефон на пять процентов, " +
                    "плата станет дороже на 14 рублей. Поскольку в задаче спрашивается, сколько рублей придется платить за " +
                    "телефон в месяц после подорожания, нам остается лишь выполнить простейшее арифметическое действие: " +
                    "280+14=294 (рубля)." ,"294")
        val task10 = Task(10, 7, "", "Ваня потратил в компьютерном магазине 600 рублей. " +
                "На покупку кабеля он израсходовал 15% этой суммы, а на покупку мыши— 30% этой суммы. Сколько рублей стоили " +
                "остальные товары, купленные Ваней?",
            "15% + 30% = 45% - потратил на кабель и мышь\n" +
                    "100% - 45% = 55% - потратил на остальные товары\n" +
                    "600 * 0,55 = 330 (руб)" ,"330")

        val listTasks = mutableListOf(task0, task1, task2, task3, task4, task5, task6, task7, task8, task9, task10)
        Database.allTasks.addAll(listTasks)

        val answer0 = Answer(0, "5", false, 0)
        val answer1 = Answer(1, "21", true, 1)
        val answer2 = Answer(2, "26", false, 2)
        val answer3 = Answer(3, "36", true, 3)
        val answer4 = Answer(4, "10", true, 4)
        val answer5 = Answer(5, "7", true, 5)
        val answer6 = Answer(6, "13", true, 6)
        val answer7 = Answer(7, "10", true, 7)
        val answer8 = Answer(8, "2", true, 8)
        val answer9 = Answer(9, "10", false, 9)
        val answer10 = Answer(10, "330", true, 10)

        val listAnswers = mutableListOf(answer0, answer1, answer2, answer3, answer4, answer5, answer6,
            answer7, answer8, answer9, answer10)
        Database.answerOnTask.addAll(listAnswers)
    }
}
