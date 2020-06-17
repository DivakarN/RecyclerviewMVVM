package com.sysaxiom.recyclerviewmvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.sysaxiom.recyclerviewmvvm.utils.NoInternetException
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.NO_INTERNET_CONNECTTION
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException(NO_INTERNET_CONNECTTION)
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        try {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetwork = connectivityManager?.activeNetwork
            if(activeNetwork!=null){
                val capabilities = connectivityManager?.getNetworkCapabilities(activeNetwork)
                result = capabilities!=null
            } else{
                result = false
            }
        } catch (e: Exception) {
            Log.d("NetworkHandler", e.toString())
        }
        return result
    }

}