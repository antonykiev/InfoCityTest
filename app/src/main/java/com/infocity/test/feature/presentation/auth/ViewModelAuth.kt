package com.infocity.test.feature.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infocity.test.feature.domain.usecase.AuthUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelAuth @Inject constructor(
    private val authUser: AuthUser
): ViewModel() {

    private val _uiAuthState = MutableStateFlow<UIAuthState>(UIAuthState.Loading)
    val uiAuthState = _uiAuthState.asStateFlow()

    init {
        viewModelScope.launch {
            authUser.invoke()
                .collect {
                    _uiAuthState.emit(UIAuthState.Success)
                }
        }
    }

    sealed class UIAuthState {
        object Loading: UIAuthState()
        object Success: UIAuthState()
        object Err: UIAuthState()
    }

}