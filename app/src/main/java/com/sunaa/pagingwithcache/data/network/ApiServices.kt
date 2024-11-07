package com.sunaa.pagingwithcache.data.network

import com.sunaa.pagingwithcache.data.network.dto.TourDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("series")
    suspend fun getSeriesList(
        @Query("apikey") apiKey: String = "62087e8c-c5b8-491a-adf2-bd89ffb81dc3",
        @Query("offset") offset: Int
    ) : Response<TourDto>

}