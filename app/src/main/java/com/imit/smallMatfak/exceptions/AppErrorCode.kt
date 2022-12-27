package com.imit.smallMatfak.exceptions

enum class AppErrorCode(errorString: String) {

    EMPTY_LOGIN("Введите логин"),
    WRONG_LOGIN("Неверный логин"),
    EMPTY_PASSWORD("Введите пароль"),
    WRONG_PASSWORD("Неверный пароль");

    val errorString: String = errorString
}