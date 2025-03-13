package com.aantrvn.expert1.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.aantrvn.expert1.core.data.source.local.entity.MoviesEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateFavoriteMovies(movies: MoviesEntity)

}