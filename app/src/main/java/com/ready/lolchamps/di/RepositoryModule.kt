package com.ready.lolchamps.di

import com.ready.lolchamps.repository.DetailRepository
import com.ready.lolchamps.repository.DetailRepositoryImpl
import com.ready.lolchamps.repository.MainRepository
import com.ready.lolchamps.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    abstract fun provideDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}