package com.aantrvn.expert1.core.data.source.remote

import android.util.Log
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

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.movies
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.movies))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error("HTTP Error: ${e.message()}"))
            } catch (e: IOException) {
                emit(ApiResponse.Error("Network Error: ${e.message}"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Unknown Error: ${e.message}"))
                Log.e("RemoteDataSource", "Exception: ", e)
            }
        }.flowOn(Dispatchers.IO)
    }
}