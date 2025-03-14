package com.aantrvn.expert1.core.data.source.remote.network

import com.aantrvn.expert1.core.data.source.remote.response.ListMovieResponse
import com.aantrvn.expert1.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") movieId: Int,
        ): ListMovieResponse

    @GET("movie/popular") // Sesuai endpoint dari TMDB
    suspend fun getMovieList(

    ): ListMovieResponse
}
