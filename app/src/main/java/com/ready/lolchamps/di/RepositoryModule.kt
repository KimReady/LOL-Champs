package com.ready.lolchamps.di

import com.ready.lolchamps.db.ChampionDao
import com.ready.lolchamps.network.ChampionService
import com.ready.lolchamps.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        championService: ChampionService,
        championDao: ChampionDao
    ): MainRepository = MainRepository(championService, championDao)
}