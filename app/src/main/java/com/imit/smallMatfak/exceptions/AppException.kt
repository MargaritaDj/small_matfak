package com.imit.smallMatfak.exceptions

class AppException(appErrorCode: AppErrorCode): Exception() {
    val appErrorCode: AppErrorCode = appErrorCode

}