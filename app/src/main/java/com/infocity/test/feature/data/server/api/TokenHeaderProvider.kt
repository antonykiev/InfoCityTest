package com.infocity.test.feature.data.server.api

object TokenHeaderProvider {

    fun provideHeader(token: String): String {
        return "Bearer $token"
    }
}