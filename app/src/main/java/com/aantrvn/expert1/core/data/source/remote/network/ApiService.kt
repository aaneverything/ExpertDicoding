package com.aantrvn.expert1.core.data.source.remote.network

import com.aantrvn.expert1.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListMovieResponse
}