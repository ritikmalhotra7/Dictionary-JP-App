package com.example.dictionaryjpapp.core.utils

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null, message: String? = null) :
        ResponseState<T>(data = data, message = message)

    class Error<T>(data: T? = null, message: String) :
        ResponseState<T>(data = data, message = message)

    class Success<T>(data: T, message: String? = null) :
        ResponseState<T>(data = data, message = message)
}