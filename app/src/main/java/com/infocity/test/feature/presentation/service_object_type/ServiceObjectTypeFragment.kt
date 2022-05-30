package com.infocity.test.feature.presentation.service_object_type

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.infocity.test.R
import com.infocity.test.databinding.FragmentServiceObjectTypeBinding
import com.infocity.test.di.Injectable
import com.infocity.test.feature.presentation.auth.ViewModelAuth
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ServiceObjectTypeFragment: Fragment(R.layout.fragment_service_object_type),
    HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private val binding by viewBinding(FragmentServiceObjectTypeBinding::bind)

    private val serviceObjectTypeViewModel by viewModels<ServiceObjectTypeViewModel> { viewModelFactory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            serviceObjectTypeViewModel.uiServiceState.collectLatest {
                Log.d("infocity", "ServiceObjectTypeFragment quantity = $it")
            }
        }
    }

}