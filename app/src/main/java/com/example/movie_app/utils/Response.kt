package com.example.movie_app.utils

data class Response<out T>(val status: ConnectionStates, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Response<T> =
            Response(status = ConnectionStates.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Response<T> =
            Response(status = ConnectionStates.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Response<T> =
            Response(status = ConnectionStates.LOADING, data = data, message = null)
    }
}