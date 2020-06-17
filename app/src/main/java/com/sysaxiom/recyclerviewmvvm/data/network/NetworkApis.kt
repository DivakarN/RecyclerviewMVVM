package com.sysaxiom.recyclerviewmvvm.data.network

import com.sysaxiom.recyclerviewmvvm.data.models.AppointmentResponse
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface NetworkApis {

    @GET(UrlsFields.GET_APPOINTMENT)
    suspend fun getAppointment(
        @Query(UrlsFields.ID) id: String
    ) : Response<AppointmentResponse>


    companion object{

        operator fun invoke( networkConnectionInterceptor: NetworkConnectionInterceptor) : NetworkApis {

            val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(UrlsFields.MAXIMUM_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(UrlsFields.BaseURL)
                .client(okHttpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApis::class.java)

        }
    }
}