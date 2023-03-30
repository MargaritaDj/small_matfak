package com.imit.smallMatfak.model

class Teacher(firstName: String, lastName: String, patronymic: String, imageHero: Int,
              login: String, password: String,
              var listClasses: ArrayList<Int> = arrayListOf(),
              var listTasks: ArrayList<Task> = arrayListOf()
)
    : User(firstName, lastName, patronymic, imageHero, login, password)