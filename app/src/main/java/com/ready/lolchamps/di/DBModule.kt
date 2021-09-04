package com.ready.lolchamps.di

import android.content.Context
import androidx.room.Room
import com.ready.lolchamps.db.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        imageTypeConverter: ImageTypeConverter,
        stringListTypeConverter: StringListTypeConverter,
        skinTypeConverter: SkinTypeConverter,
        spellTypeConverter: SpellTypeConverter,
        passiveTypeConverter: PassiveTypeConverter
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "LOLChampions.db")
        .fallbackToDestructiveMigration()
        .addTypeConverter(imageTypeConverter)
        .addTypeConverter(stringListTypeConverter)
        .addTypeConverter(skinTypeConverter)
        .addTypeConverter(spellTypeConverter)
        .addTypeConverter(passiveTypeConverter)
        .build()

    @Provides
    @Singleton
    fun provideChampionDao(appDatabase: AppDatabase): ChampionDao = appDatabase.championDao()

    @Provides
    @Singleton
    fun provideChampionInfoDao(appDatabase: AppDatabase): ChampionInfoDao = appDatabase.championInfoDao()

    @Provides
    @Singleton
    fun provideImageTypeConverter(moshi: Moshi): ImageTypeConverter = ImageTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideStringListTypeConverter(moshi: Moshi): StringListTypeConverter = StringListTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideSkinTypeConverter(moshi: Moshi): SkinTypeConverter = SkinTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideSpellTypeConverter(moshi: Moshi): SpellTypeConverter = SpellTypeConverter(moshi)

    @Provides
    @Singleton
    fun providePassiveTypeConverter(moshi: Moshi): PassiveTypeConverter = PassiveTypeConverter(moshi)
}