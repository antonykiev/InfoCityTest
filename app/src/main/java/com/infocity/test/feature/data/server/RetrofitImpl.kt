package com.infocity.test.feature.data.server

import com.infocity.test.feature.data.server.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitImpl(
    private val service: Api = Retrofit.Builder()
        .baseUrl(HttpRoutes.BASE_URL)
        .apply {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            client(okHttpClient)
        }
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
) : Api by service