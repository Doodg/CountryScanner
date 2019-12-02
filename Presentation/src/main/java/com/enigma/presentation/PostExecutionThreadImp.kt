package com.enigma.presentation

import com.enigma.usecase.excuter.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class PostExecutionThreadImp : PostExecutionThread {
        override val scheduler: Scheduler
            get() = AndroidSchedulers.mainThread()
}