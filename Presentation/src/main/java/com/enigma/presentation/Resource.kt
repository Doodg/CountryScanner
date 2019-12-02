package com.enigma.presentation

data class Resource<T> constructor(val values: T? = null, val state: State, val throwable: Throwable? = null) {

    constructor(state: State) : this(null, state, null)

    companion object {
        fun <T> success(t: T): Resource<T> {
            return Resource<T>(values = t, state = State.SUCCESS)
        }

        fun <T> error(throwable: Throwable? = null): Resource<T> {
            return Resource<T>(state = State.ERROR, throwable = throwable)
        }

        fun <T> loading(): Resource<T> {
            return Resource<T>(state = State.LOADING)
        }
    }

    enum class State {
        SUCCESS,
        ERROR,
        LOADING
    }
}