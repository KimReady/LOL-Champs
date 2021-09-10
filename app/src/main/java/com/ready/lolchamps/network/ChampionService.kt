package com.ready.lolchamps.network

import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.model.ChampionResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChampionService {

    @GET("data/en_US/champion.json")
    suspend fun fetchAllChampions(): Response<ChampionResponse<Champion>>
}