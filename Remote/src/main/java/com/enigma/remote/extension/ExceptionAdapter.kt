package com.enigma.remote.extension

import com.enigma.remote.retrofit.ErrorHandler
import io.reactivex.Observable

fun <T> Observable<T>.adaptExceptionError(): Observable<T> {
    return onErrorResumeNext(ErrorHandler<T>())
}