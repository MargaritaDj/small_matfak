package com.imit.smallMatfak.model

import java.util.Date

class Room(val password: String, val name: String, val classSchool: String, val date: String,
           val listTask: List<Task>)