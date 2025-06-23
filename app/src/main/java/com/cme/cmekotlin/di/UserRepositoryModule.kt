package com.cme.cmekotlin.di

import com.cme.cmekotlin.auth.UserRepository
import com.cme.cmekotlin.auth.FirebaseUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: FirebaseUserRepository
    ): UserRepository
}
