package com.ready.lolchamps.di

import android.content.Context
import com.ready.lolchamps.ui.main.ChampionAdapter
import com.ready.lolchamps.ui.main.ChampionItemDecoration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModule {

    @Provides
    @ActivityScoped
    fun provideChampionAdapter(@ActivityContext context: Context): ChampionAdapter = ChampionAdapter(context)

    @Provides
    @ActivityScoped
    fun provideChampionItemDecoration(): ChampionItemDecoration = ChampionItemDecoration()
}