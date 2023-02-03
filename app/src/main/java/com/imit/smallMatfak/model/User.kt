package com.imit.smallMatfak.model

open class User(val firstName: String, val lastName: String, val patronymic: String,
                    var imageHero: Int, val login: String, var password: String): java.io.Serializable
