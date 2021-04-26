package com.example.mapboxexample.data.webservice

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private var sRetrofit: Retrofit? = null
        private var sApiService: ApiService? = null

        private fun getClient(
            okHttpClient: OkHttpClient.Builder, baseUrl: String, context: Context
        ): Retrofit {
            sRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return sRetrofit!!
        }

        fun getService(
            okHttpClient: OkHttpClient.Builder, baseUrl: String, context: Context): ApiService {
            if (sApiService == null) {
                return getClient(
                    okHttpClient,
                    baseUrl,context
                ).create(ApiService::class.java)
            }
            return sApiService!!
        }
    }
}