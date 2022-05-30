package com.infocity.test.feature.presentation.service_object_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infocity.test.feature.domain.usecase.GetObjectTypesQuantity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceObjectTypeViewModel @Inject constructor(
    private val loadQuantityUseCase: GetObjectTypesQuantity
): ViewModel() {

    private val _uiServiceState = MutableStateFlow<UIServiceObjectTypeState>(UIServiceObjectTypeState.Loading)
    val uiServiceState = _uiServiceState.asStateFlow()

    init {
        viewModelScope.launch {
            loadQuantityUseCase.invoke()
                .collect {
                    _uiServiceState.emit(UIServiceObjectTypeState.Success(it))
                }
        }
    }

    sealed class UIServiceObjectTypeState {
        object Loading: UIServiceObjectTypeState()
        data class Success(val quantity: Int): UIServiceObjectTypeState()
        object Err: UIServiceObjectTypeState()
    }

}