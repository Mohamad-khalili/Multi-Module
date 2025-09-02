package com.multimodule.authentication

import com.multimodule.authentication.databinding.ActivityAuthBinding
import com.multimodule.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthVM>(AuthVM::class.java) {


    override fun setBinding(): ActivityAuthBinding =
        ActivityAuthBinding.inflate(layoutInflater)

}