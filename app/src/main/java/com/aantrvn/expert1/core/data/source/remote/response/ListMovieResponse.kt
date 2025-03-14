package com.aantrvn.expert1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResponse>
)
