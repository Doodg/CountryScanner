package com.enigma.usecase.excuter

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in E>(private val schedulerThread: PostExecutionThread) {
    private val disposables: CompositeDisposable = CompositeDisposable()
    abstract fun buildCompletableUseCase(params: E? = null): Completable
    open fun execute(observer: DisposableCompletableObserver, params: E? = null) {
        val observable = this.buildCompletableUseCase(params)
            .subscribeOn(Schedulers.io())
            .observeOn(schedulerThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}