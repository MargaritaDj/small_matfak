package com.imit.smallMatfak.model

class Teacher(firstName: String, lastName: String, patronymic: String, imageHero: Int,
              login: String, password: String, var email: String, var listClasses: ArrayList<Int>,
              var listTasks: ArrayList<Task>)
    : User(firstName, lastName, patronymic, imageHero, login, password)