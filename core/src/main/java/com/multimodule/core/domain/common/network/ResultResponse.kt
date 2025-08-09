package com.multimodule.core.domain.common.network

data class ResultResponse<T>(var result: T?){
    var status: Int = 0
    var message: String = ""
        set(value){
            field = value
            when{
                status in 400..499 && result == null ->{
                    if (status != 401 && status != 403){
                        if (message.isEmpty())
                            field = "Server Error"
                    }else{
                        field = "UnAuthorize"
                    }
                }
                else -> {
                    field = "Server Error"
                }
            }
        }
//    var paging: Paging? = null
    var serverTime : String? = null
}
