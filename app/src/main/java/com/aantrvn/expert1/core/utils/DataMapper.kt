package com.aantrvn.expert1.core.utils

import com.aantrvn.expert1.core.data.source.local.entity.MoviesEntity
import com.aantrvn.expert1.core.data.source.remote.response.MovieResponse
import com.aantrvn.expert1.core.domain.model.Movies

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                overview = it.overview ?: "",
                releaseDate = it.releaseDate ?: "",
                posterPath = it.posterPath ?: "",
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: Movies) = MoviesEntity(
        id = input.movieId,
        title = input.title,
        overview = input.overview,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}