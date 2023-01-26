package com.imit.smallMatfak.exceptions

enum class AppErrorCode(errorString: String) {

    EMPTY_LOGIN("Введите логин"),
    WRONG_LOGIN("Неверный логин"),
    EMPTY_PASSWORD("Введите пароль"),
    WRONG_PASSWORD("Неверный пароль"),
    EMPTY_PHONE("Введите номер телефона"),
    WRONG_PHONE("Неверный формат номера"),
    WRONG_CODE_FROM_SMS(" "),
    SHORT_PASSWORD("Пароль должен содержать не менее 6 символов");

    val errorString: String = errorString
}