package com.infocity.test.feature.data.source

import com.infocity.test.feature.data.server.api.Api
import com.infocity.test.feature.data.server.api.ApiServiceTypeObject

class GetServiceObjectTypesRemoteSource(private val api: Api): ApiServiceTypeObject by api