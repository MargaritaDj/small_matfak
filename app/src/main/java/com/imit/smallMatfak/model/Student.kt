package com.imit.smallMatfak.model

import android.os.Parcel
import android.os.Parcelable

class Student(firstName: String, lastName: String, patronymic: String, imageHero: Int,
              login: String, password: String, var classSchool: Int)
    : User(firstName, lastName, patronymic, imageHero, login, password)
