package com.xing.binge.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xing.binge.api.mocks.MockInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//ApiFactory to create Api
object ApiFactory{
    //TODO:UNUSED
    //Creating Auth Interceptor to add api_key query in front of all the requests. <CODE SHOULD BE AS CLOSE TO PROD READY AS POSSIBLE>
    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            //.addQueryParameter("api_key", key) NO KEY HERE BUT THERE SHOULD BE ONE POSSIBLY COMING FROM A CONFIG FILE
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }


    private val mockInterceptor = MockInterceptor ()

    //OkhttpClient for building http request url
    private val moviesApiClient = OkHttpClient().newBuilder()
        .addInterceptor(mockInterceptor)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(moviesApiClient)
        .baseUrl("http://example.com") // DOCKER CONFIG
        .addConverterFactory(MoshiConverterFactory.create()) // PARSING
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // NO JAVARX here
        .build()

    val moviesApi : MoviesApi = retrofit().create(MoviesApi::class.java)
}