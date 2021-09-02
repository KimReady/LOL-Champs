package com.ready.lolchamps.di

import com.ready.lolchamps.BuildConfig
import com.ready.lolchamps.network.ChampionService
import com.ready.lolchamps.network.RequestDebugInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRequestDebugInterceptor(): RequestDebugInterceptor {
        return RequestDebugInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: RequestDebugInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .callTimeout(1000, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLolChampionsService(retrofit: Retrofit): ChampionService {
        return retrofit.create(ChampionService::class.java)
    }
}