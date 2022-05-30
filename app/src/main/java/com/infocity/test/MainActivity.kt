package com.infocity.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.infocity.test.databinding.ActivityMainBinding
import com.infocity.test.di.Injectable
import com.infocity.test.feature.presentation.auth.ViewModelAuth
import com.infocity.test.feature.presentation.service_object_type.ServiceObjectTypeFragment
import com.infocity.test.feature.presentation.service_object_type.UploadWorkObjectType
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private val userListViewModel by viewModels<ViewModelAuth> { viewModelFactory }

    private val binding by viewBinding(ActivityMainBinding::bind)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("infocity", "start...")

        lifecycleScope.launchWhenStarted {
            userListViewModel.uiAuthState.collectLatest {
                when (it) {
                    ViewModelAuth.UIAuthState.Err -> onErrAuth()
                    ViewModelAuth.UIAuthState.Loading -> onLoadingAuth()
                    ViewModelAuth.UIAuthState.Success -> onSuccessAuth()
                }
            }
        }
    }

    private fun onErrAuth() {
        binding.progressBar.visibility = View.INVISIBLE

    }

    private fun onLoadingAuth() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onSuccessAuth() {
        Log.d("infocity", "onSuccessAuth")

        binding.progressBar.visibility = View.INVISIBLE

        supportFragmentManager.beginTransaction()
            .replace(binding.root.id, ServiceObjectTypeFragment())
            .commit()


//        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorkObjectType>()
//            .build()
//        val data = Data.Builder()
//        data.pu
//
//        WorkManager.getInstance(this@MainActivity)
//            .enqueue(uploadWorkRequest)
    }

}