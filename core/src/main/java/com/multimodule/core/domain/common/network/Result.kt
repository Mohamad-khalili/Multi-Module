package com.multimodule.core.domain.common.network


sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class GenericError(val code: Int? = null, val message: String? = null) : Result<Nothing>()
    object NetworkError : Result<Nothing>()

}
