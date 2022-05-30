package com.infocity.test.feature.presentation.service_object_type

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.infocity.test.R
import com.infocity.test.databinding.FragmentServiceObjectTypeBinding
import com.infocity.test.di.Injectable
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ServiceObjectTypeFragment: Fragment(R.layout.fragment_service_object_type), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentServiceObjectTypeBinding::bind)

    private val serviceObjectTypeViewModel by activityViewModels<ServiceObjectTypeViewModel> { viewModelFactory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            serviceObjectTypeViewModel.uiServiceState.collectLatest {
                Log.d("infocity", "ServiceObjectTypeFragment quantity = $it")
            }
        }
    }

}