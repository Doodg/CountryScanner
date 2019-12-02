package com.enigma.remote.exceptionhandler

import io.reactivex.Observable
import io.reactivex.functions.Function
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.xml.ws.WebServiceException

class ExceptionAdapter<T> : Function<Throwable, Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
        return Observable.error(mapException(t))
    }

    fun mapException(t: Throwable): Throwable {
        if (t is UnknownHostException || t is SocketTimeoutException || t is SocketTimeoutException || t is WebServiceException) {
            return NetworkError()
        } else {
            return UnknownException()
        }
    }
}