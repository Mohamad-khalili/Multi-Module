package com.multimodule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimodule.core.domain.common.network.Result
import com.multimodule.core.domain.interactor.SplashInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVm @Inject constructor(val interactor: SplashInteractor): ViewModel()  {


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