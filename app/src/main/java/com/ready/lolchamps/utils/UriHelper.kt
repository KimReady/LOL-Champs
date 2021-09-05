package com.ready.lolchamps.utils

import com.ready.lolchamps.BuildConfig
import com.ready.lolchamps.model.ChampionInfo


internal fun getSplashImageUri(name: String?) =
    if (!name.isNullOrEmpty()) "${BuildConfig.BASE_URL}/img/champion/splash/${name}_0.jpg"
    else null

internal fun getSpellImageUri(name: String?) =
    if (!name.isNullOrEmpty()) "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/spell/${name}.png"
    else null

internal fun getPassiveImageUri(fileName: String?) =
    if (!fileName.isNullOrEmpty()) "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/passive/${fileName}"
    else null

internal fun getSkinImageUri(name: String, skinNum: Int) =
    "${BuildConfig.BASE_URL}/img/champion/loading/${name}_${skinNum}.jpg"
