package com.cme.cmekotlin.di
import com.cme.cmekotlin.signup.data.repo.VerificationModel
import com.cme.cmekotlin.signup.data.repo.VerificationRepository
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
        impl: VerificationRepository
    ): VerificationModel
}
