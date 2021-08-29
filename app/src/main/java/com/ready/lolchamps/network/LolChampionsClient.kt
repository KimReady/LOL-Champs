package com.ready.lolchamps.network

import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject


class LolChampionsClient @Inject constructor(
    private val service: LolChampionsService
) {

}