package com.multimodule.base

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.multimodule.commons.utils.Route
import com.multimodule.core.data.datasource.AuthLocalDataSource
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel>(private val modelClass: Class<V>) :
    AppCompatActivity() {
//    private var loadingDialog: LoadingDialog? = null

    @Inject
    lateinit var authLocalDataSource: AuthLocalDataSource
    private lateinit var userLoggedInFunLocal: () -> (Unit)

    protected lateinit var binding: T
    private val shareVM: ShareViewModel by viewModels()
    open lateinit var shareViewModel: ShareViewModel

    @Inject
    lateinit var viewModel: V


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (viewModel as BaseViewModel).sharedViewModel = shareVM
        shareViewModel = shareVM
        binding = this.setBinding()
        setContentView(binding.root)
        initViews()
        observers()
        listeners()
    }


    abstract fun setBinding(): T

    protected open fun initViews() {}
    protected open fun observers() {}
    protected open fun listeners() {}

    private val authActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                userLoggedInFunLocal()
            }
        }

    /**
     * check Token and if user doesn't login and the isForced is true  then redirect to AuthActivity.
     * otherwise the userHasLogined Function must be run.
     * **/
    fun loginCheck(userLoggedInFun: () -> (Unit), isForced: Boolean) {
        if (checkToken().isNullOrEmpty()) {
            if (isForced) {
                userLoggedInFunLocal = userLoggedInFun
                authActivityResult.launch(
                    Route.setupIntent(this, Route.AUTH_ACTIVITY, true)
                )
            }
        } else {
            userLoggedInFun()
        }
    }


    fun checkToken(): String {
        //check token
        return ""
    }

}