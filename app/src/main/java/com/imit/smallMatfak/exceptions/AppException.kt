package com.imit.smallMatfak.exceptions

open class AppException(appErrorCode: AppErrorCode): Exception() {
    val appErrorCode: AppErrorCode = appErrorCode

}