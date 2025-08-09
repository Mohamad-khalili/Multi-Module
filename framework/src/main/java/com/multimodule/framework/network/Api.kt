package com.multimodule.framework.network

import com.google.gson.Gson
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.common.network.ResultResponse
import retrofit2.Response

object Api {
    inline fun <reified T: Any> result(service: () -> Response<T>): Result<T>{
        var errorCode: Int? = null
        var errorMsg: String? = null

        return try {
            val result = service()
            errorCode = result.code()
            errorMsg = result.raw().message
            if (result.isSuccessful){
                Result.Success(result.body()!!)
            }else{
                if (result.code() != 401){
                    val gSon = Gson()
                    val baseApiError = gSon.fromJson(result.errorBody().toString(),ResultResponse::class.java)
                    if (baseApiError != null && baseApiError.message.isNotEmpty())
                        Result.GenericError(result.code(),baseApiError.message)
                    else Result.GenericError(result.code(),errorMsg)
                }else{
                    Result.GenericError(result.code(),"Please Login to Continue")
                }

            }

        }catch (e: Exception){
            e.printStackTrace()
            if (errorCode != null && errorMsg != null){
                Result.GenericError(errorCode,errorMsg)
            }

            Result.NetworkError
        }
    }
}