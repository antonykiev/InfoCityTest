package com.infocity.test.feature.domain.utils

sealed class UserState {

    sealed class AuthState {

        object AuthSuccess : AuthState()

        object AuthFailed : AuthState()

    }

    sealed class TokenState {

        object IsValid : TokenState()

        object IsOutDated : TokenState()

    }

}
