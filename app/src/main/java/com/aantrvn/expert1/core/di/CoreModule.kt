package com.aantrvn.expert1.core.di

import androidx.room.Room
import com.aantrvn.expert1.core.data.MovieRepository
import com.aantrvn.expert1.core.data.source.local.LocalDataSource
import com.aantrvn.expert1.core.data.source.local.room.MovieDatabase
import com.aantrvn.expert1.core.data.source.remote.RemoteDataSource
import com.aantrvn.expert1.core.data.source.remote.auth.AuthInterceptor
import com.aantrvn.expert1.core.data.source.remote.network.ApiService
import com.aantrvn.expert1.core.domain.repository.IMovieRepository
import com.aantrvn.expert1.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())  // Pastikan ini ada!
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}