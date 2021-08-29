package com.ready.lolchamps.di

import android.content.Context
import com.ready.lolchamps.network.LolChampionsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val locale = context.resources.configuration.locales[0]
        return Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com/cdn/11.13.1/data/${locale.language}_${locale.country}/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLolChampionsService(retrofit: Retrofit): LolChampionsService {
        return retrofit.create(LolChampionsService::class.java)
    }
}