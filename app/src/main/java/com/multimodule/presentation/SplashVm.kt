package com.multimodule.presentation

import androidx.lifecycle.viewModelScope
import com.multimodule.base.BaseViewModel
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.interactor.SplashInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashVm @Inject constructor(val interactor: SplashInteractor) : BaseViewModel() {


    fun getAppSettings() {
        viewModelScope.launch {
            when ( val res = interactor.getAppSettings()){
               is Result.Success ->{}
               is Result.GenericError ->{}
               is Result.NetworkError ->{}
            }
        }
    }
}