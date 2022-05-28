package com.infocity.test.feature.data.server.response

data class UserAuthResponse(
    val accessToken: String,
    val companyId: String,
    val email: String,
    val login: String,
    val memberId: String,
    val name: String,
    val personId: String,
    val positionId: String,
    val roleId: String,
    val rules: List<RuleResponse>
)