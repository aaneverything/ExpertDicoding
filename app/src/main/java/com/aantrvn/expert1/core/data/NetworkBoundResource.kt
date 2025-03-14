package com.aantrvn.expert1.core.data

import android.util.Log
import com.aantrvn.expert1.core.data.source.remote.network.ApiResponse
import com.aantrvn.expert1.core.data.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        Log.d("DEBUG_FETCH", "NetworkBoundResource: Memulai fetch data...")

        val dbSource = loadFromDB().firstOrNull()
        Log.d("DEBUG_FETCH", "NetworkBoundResource: Data dari DB -> ${dbSource?.toString()}")

        if (shouldFetch(dbSource)) {
            Log.d("DEBUG_FETCH", "NetworkBoundResource: Fetching dari API...")
            emit(Resource.Loading())

            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    Log.d("DEBUG_FETCH", "NetworkBoundResource: API Success -> ${apiResponse} items")
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    Log.d("DEBUG_FETCH", "NetworkBoundResource: API Empty Response")
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    Log.e("DEBUG_FETCH", "NetworkBoundResource: API Error -> ${apiResponse.errorMessage}")
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage, dbSource))
                }
            }
        } else {
            Log.d("DEBUG_FETCH", "NetworkBoundResource: Menggunakan data dari DB.")
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}
