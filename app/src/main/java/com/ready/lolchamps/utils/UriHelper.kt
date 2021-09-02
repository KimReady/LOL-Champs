package com.ready.lolchamps.utils

import com.ready.lolchamps.BuildConfig
import com.ready.lolchamps.model.ChampionInfo


internal fun getSplashImageUri(name: String) =
    "${BuildConfig.BASE_URL}/img/champion/splash/${name}_0.jpg"

internal fun getSkinImageUri(name: String, skin: ChampionInfo.Skin) =
    "${BuildConfig.BASE_URL}/img/champion/loading/${name}_${skin.num}.jpg"

internal fun getSkillImageUri(name: String) =
    "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/spell/${name}.png"

internal fun getPassiveImageUri(fileName: String) =
    "${BuildConfig.BASE_URL}/${BuildConfig.LOL_VERSION}/img/passive/${fileName}"