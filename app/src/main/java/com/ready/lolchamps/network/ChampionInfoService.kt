package com.ready.lolchamps.network

import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.model.ChampionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ChampionInfoService {

    @GET("data/en_US/champion/{id}.json")
    suspend fun fetchChampionInfo(@Path("id") id: String): Response<ChampionResponse<ChampionInfo>>
}