package com.ready.lolchamps.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RequestDebugInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.d("Request : $request")
        val response = chain.proceed(request)
        Timber.d("Response : $response")
        return response
    }
}