package com.example.whatsnews.vo

data class Resource<out T>(val status: Status, val data: T?, val msg: String?) {
    companion object {

        fun <T> succes(data: T?): Resource<T> {
            return Resource(Status.SUCCES, data, null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

    }
}