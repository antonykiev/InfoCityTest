package com.infocity.test.feature.data.server.request

data class UserAuthDto(
    val login: String,
    val password: String,
    val returnUrl: String = "string"
)