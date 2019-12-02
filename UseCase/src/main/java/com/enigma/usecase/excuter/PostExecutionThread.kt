package com.enigma.usecase.excuter

import io.reactivex.Scheduler


interface PostExecutionThread {
    val scheduler: Scheduler
}