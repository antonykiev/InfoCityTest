package com.infocity.test.feature.domain.utils

import com.infocity.test.feature.data.store.User

object UserStateProvider {

    fun getAuthState(user: User): UserState.AuthState {
        return when (user.accessToken) {
            null -> UserState.AuthState.AuthFailed
            else -> UserState.AuthState.AuthSuccess
        }
    }

}