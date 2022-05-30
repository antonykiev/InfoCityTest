package com.infocity.test.feature.presentation.service_object_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infocity.test.feature.domain.usecase.GetObjectTypes
import com.infocity.test.feature.domain.usecase.GetObjectTypesQuantityLoader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceObjectTypeViewModel @Inject constructor(
    private val loadQuantityUseCase: GetObjectTypesQuantityLoader,
    private val getObjectTypes: GetObjectTypes
): ViewModel() {

    private val _uiServiceState = MutableStateFlow<UIServiceObjectTypeState>(UIServiceObjectTypeState.Loading)
    val uiServiceState = _uiServiceState.asStateFlow()

    init {
        viewModelScope.launch {
            loadQuantityUseCase.invoke()
                .collect {
                    _uiServiceState.emit(
                        UIServiceObjectTypeState.Success(it.size)
                    )
                }
        }
    }

    suspend fun loadFiltered(id: String?) = getObjectTypes.invoke(id)
        .map {
            it.sortedBy { !it.hasChildren }
        }

    sealed class UIServiceObjectTypeState {
        object Loading: UIServiceObjectTypeState()
        data class Success(val quantity: Int): UIServiceObjectTypeState()
        object Err: UIServiceObjectTypeState()
    }

}