package com.aantrvn.expert1.core.domain.usecase

import com.aantrvn.expert1.core.data.Resource
import com.aantrvn.expert1.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)
}