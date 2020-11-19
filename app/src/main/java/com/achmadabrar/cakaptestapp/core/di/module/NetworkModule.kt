package com.achmadabrar.cakaptestapp.core.di.module

import com.achmadabrar.cakaptestapp.BuildConfig
import com.achmadabrar.cakaptestapp.data.network.ContactApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providePostApi(retrofit: Retrofit): ContactApi {
        return retrofit.create(ContactApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addNetworkInterceptor {
            val originalRequest = it.request()
            val requestBuilder = originalRequest.newBuilder()
                .header("Authorization", "Bearer E4lZ73uSzAFGFt7WYzKOP17ovI9lnTGj")
            it.proceed(requestBuilder.build())
        }

        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl("https://rest-api-cloud-functions.firebaseapp.com/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


}