package com.aantrvn.expert1.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MoviesEntity(
    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "overview")
    val overview: String = "",

    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",

    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",

    @ColumnInfo(name = "isfavorite")
    var isFavorite: Boolean = false

)