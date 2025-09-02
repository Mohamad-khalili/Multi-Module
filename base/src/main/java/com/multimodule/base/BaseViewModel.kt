package com.multimodule.base

import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {


    lateinit var sharedViewModel: ShareViewModel
}