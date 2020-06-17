package com.sysaxiom.recyclerviewmvvm.data.network

import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.BaseURL
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.MAXIMUM_TIMEOUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHandler {

    companion object{

        operator fun invoke( networkConnectionInterceptor: NetworkConnectionInterceptor) : NetworkApis {

            val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .client(okHttpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApis::class.java)

        }
    }

}