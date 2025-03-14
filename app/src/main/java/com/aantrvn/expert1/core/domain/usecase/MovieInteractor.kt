package com.aantrvn.expert1.core.domain.usecase

import com.aantrvn.expert1.core.domain.model.Movies
import com.aantrvn.expert1.core.domain.repository.IMovieRepository

class MovieInteractor(
    private val movieRepository: IMovieRepository
) : MoviesUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()
    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()
    override fun setFavoriteMovies(movies: Movies, state: Boolean) = movieRepository.setFavoriteMovies(movies, state)
}