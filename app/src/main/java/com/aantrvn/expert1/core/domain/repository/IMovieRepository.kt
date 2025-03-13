package com.aantrvn.expert1.core.domain.repository


import com.aantrvn.expert1.core.domain.model.Movies
import com.aantrvn.expert1.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movie: Movies, newState: Boolean)
}