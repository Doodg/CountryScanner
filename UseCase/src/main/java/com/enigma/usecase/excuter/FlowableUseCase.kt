package com.enigma.usecase.excuter

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in E>(private val schedulerThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()
    abstract fun buildUseCaseFlowable(params: E? = null): Flowable<T>

    open fun execute(observer: DisposableSubscriber<T>, params: E? = null) {
        val observable = this.buildUseCaseFlowable(params)
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