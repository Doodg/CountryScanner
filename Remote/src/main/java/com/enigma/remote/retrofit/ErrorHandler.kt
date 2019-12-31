package com.enigma.remote.retrofit

import com.enigma.remote.retrofit.errortype.NetworkException
import com.enigma.remote.retrofit.errortype.UnknownException
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler<T> : Function<Throwable, Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
        return Observable.error(errorMapper(t))
    }

    fun errorMapper(throwable: Throwable): Throwable {
        if (throwable is UnknownHostException || throwable is SocketException || throwable is SocketTimeoutException) {
            return NetworkException()
        } else
            return UnknownException()
    }
}