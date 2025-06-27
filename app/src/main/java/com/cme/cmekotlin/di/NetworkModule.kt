package com.cme.cmekotlin.di

import com.cme.cmekotlin.network.MockVerificationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3001")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideMockVerificationApi(retrofit: Retrofit): MockVerificationApi =
        retrofit.create(MockVerificationApi::class.java)
}
