package com.yunusbedir.playground.di

import android.util.Log
import com.yunusbedir.playground.BuildConfig
import com.yunusbedir.playground.data.remote.RemoteConstants
import com.yunusbedir.playground.data.remote.service.MockApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.MOCK_API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(RemoteConstants.TIME_OUT, TimeUnit.SECONDS)
            readTimeout(RemoteConstants.TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(RemoteConstants.TIME_OUT, TimeUnit.SECONDS)
            val interceptor = HttpLoggingInterceptor { message ->
                try {
                    if (BuildConfig.DEBUG) {
                        Log.d("okhttp", message)
                    }
                } catch (_: Exception) {

                }
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): MockApiService {
        return retrofit.create(MockApiService::class.java)
    }

}
