package com.aantrvn.expert1.core.data



import android.util.Log
import com.aantrvn.expert1.BuildConfig
import com.aantrvn.expert1.core.data.source.local.LocalDataSource
import com.aantrvn.expert1.core.data.source.remote.RemoteDataSource
import com.aantrvn.expert1.core.data.source.remote.network.ApiResponse
import com.aantrvn.expert1.core.data.source.remote.response.MovieResponse
import com.aantrvn.expert1.core.domain.model.Movies
import com.aantrvn.expert1.core.domain.repository.IMovieRepository
import com.aantrvn.expert1.core.utils.AppExecutors
import com.aantrvn.expert1.core.utils.DataMapper
import com.aantrvn.expert1.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                Log.d("DEBUG_FETCH", "Memuat data dari database lokal...")
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                Log.d("DEBUG_FETCH", "Cek apakah perlu fetch dari API: ${data.isNullOrEmpty()}")
                return data.isNullOrEmpty()
            }


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                val apiKey = BuildConfig.TMDB_API_KEY
                return remoteDataSource.getAllMovies(apiKey)
            }


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                Log.d("DEBUG_FETCH", "Menyimpan data API ke database lokal...")
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()


    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getAllMovies().map {
            DataMapper.mapEntitiesToDomain(it).orEmpty()
        }
    }

    override fun setFavoriteMovies(movie: Movies, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movieEntity, newState) }
    }
}