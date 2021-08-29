package com.ready.lolchamps.network

import com.ready.lolchamps.model.Champion
import retrofit2.Response
import retrofit2.http.GET

interface LolChampionsService {


    @GET("champions.json")
    fun fetchAllChampions(): Response<List<Champion>>
}