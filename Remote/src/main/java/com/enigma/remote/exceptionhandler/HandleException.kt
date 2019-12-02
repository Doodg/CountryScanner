package com.enigma.remote.exceptionhandler

import io.reactivex.Observable

fun <T> Observable<T>.handleRequestExceptions(): Observable<T> {
    return onErrorResumeNext(ExceptionAdapter<T>())
}