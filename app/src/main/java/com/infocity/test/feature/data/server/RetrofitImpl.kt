package com.infocity.test.feature.data.server

import com.infocity.test.feature.data.server.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitImpl(
    private val service: Api = Retrofit.Builder()
        .baseUrl(HttpRoutes.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
) : Api by service