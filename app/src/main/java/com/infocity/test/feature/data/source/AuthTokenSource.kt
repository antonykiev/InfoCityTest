package com.infocity.test.feature.data.source

import com.infocity.test.feature.data.server.api.Api
import com.infocity.test.feature.data.server.api.ApiAuthToken

class AuthTokenSource(private val api: Api): ApiAuthToken by api