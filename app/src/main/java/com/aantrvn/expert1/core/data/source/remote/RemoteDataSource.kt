package com.aantrvn.expert1.core.data.source.remote

import android.util.Log
import com.aantrvn.expert1.BuildConfig
import com.aantrvn.expert1.core.data.source.remote.network.ApiResponse
import com.aantrvn.expert1.core.data.source.remote.network.ApiService
import com.aantrvn.expert1.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.io.IOException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(apiKey: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                Log.d("RemoteDataSource", "Fetching movies from API...")

                val response = apiService.getMovieList()
                val dataArray = response.results?: emptyList()

                Log.d("RemoteDataSource", "API Response: $response") // Cek responsenya


                if (dataArray.isNotEmpty()) {
                    Log.d("RemoteDataSource", "Movies list is NOT empty. Total: ${dataArray.size}")
                    emit(ApiResponse.Success(dataArray))
                } else {
                    Log.d("RemoteDataSource", "Movies list is EMPTY")
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                Log.e("RemoteDataSource", "HTTP Exception: ${e.response()?.errorBody()?.string()}")
                emit(ApiResponse.Error("HTTP Error: ${e.message()}"))
            } catch (e: IOException) {
                Log.e("RemoteDataSource", "Network Error: ${e.message}")
                emit(ApiResponse.Error("Network Error: ${e.message}"))
            } catch (e: Exception) {
                Log.e("RemoteDataSource", "Unknown Error: ${e.message}", e)
                emit(ApiResponse.Error("Unknown Error: ${e.message}"))
            }
        }.flowOn(Dispatchers.IO)
    }
}
