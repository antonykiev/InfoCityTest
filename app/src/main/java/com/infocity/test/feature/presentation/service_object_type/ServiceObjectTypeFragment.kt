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
import com.infocity.test.feature.data.store.ServiceObjectType
import com.infocity.test.feature.presentation.auth.Divider
import com.infocity.test.feature.presentation.cell.ViewHolderSot
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ServiceObjectTypeFragment: Fragment(R.layout.fragment_service_object_type), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding by viewBinding(FragmentServiceObjectTypeBinding::bind)

    private val serviceObjectTypeViewModel by activityViewModels<ServiceObjectTypeViewModel> { viewModelFactory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parenId = runCatching {
            requireArguments().getString(SOT_PARENT_ID_KEY)
        }.getOrNull()

        if (parenId != null) {
            load(parenId)
            return
        }

        lifecycleScope.launchWhenStarted {
            serviceObjectTypeViewModel.uiServiceState.collectLatest {
                load()
            }
        }
    }

    private fun load(id: String? = null) {
        lifecycleScope.launchWhenStarted {
            serviceObjectTypeViewModel
                .loadFiltered(id)
                .collectLatest(::onServiceObjectTypeLoaded)
        }
    }

    private fun onServiceObjectTypeLoaded(list: List<ServiceObjectType>) {
        val adapter = ViewHolderSot(list, ::onClicked)
        binding.progress.visibility = View.INVISIBLE
        binding.tvLoading.visibility = View.INVISIBLE
        binding.rvSot.visibility = View.VISIBLE
        binding.rvSot.adapter = adapter
        binding.rvSot.addItemDecoration(Divider(requireContext(), R.drawable.line_divider))
    }

    private fun onClicked(sot: ServiceObjectType) {

        val bundle = Bundle().apply {
            putString(SOT_PARENT_ID_KEY, sot.id)
        }

        val fragment = ServiceObjectTypeFragment().apply {
            arguments = bundle
        }

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.root, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val SOT_PARENT_ID_KEY = "sot_parent_id_key"
    }

}