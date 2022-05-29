package com.infocity.test.feature.domain.data

data class UserDomain(
    val login: String,
    val password: String,
    val accessToken: String?
)
