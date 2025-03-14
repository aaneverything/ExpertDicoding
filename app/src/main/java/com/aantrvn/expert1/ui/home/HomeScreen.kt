package com.aantrvn.expert1.ui.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aantrvn.expert1.core.data.Resource
import com.aantrvn.expert1.core.domain.model.Movies
import com.aantrvn.expert1.ui.components.MovieItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    Log.d("DEBUG_FETCH", "HomeScreen Composable dipanggil")

    val moviesState by viewModel.movies.collectAsState()

    when (moviesState) {
        is Resource.Loading -> {
            Log.d("DEBUG_FETCH", "Data: ${moviesState.data}")
            CircularProgressIndicator()
        }

        is Resource.Success -> {
            Log.d("DEBUG_FETCH", "Data berhasil diambil: ${moviesState.data?.size} film")
            LazyColumn {
                items(moviesState.data.orEmpty()) { movie ->
                    MovieItem(

                        title = movie.title,
                        releaseDate = movie.releaseDate,
                        overview = movie.overview,
                        poster = movie.posterPath,
                        onClick = { /* Navigasi ke detail */ }
                    )
                    Log.d("DEBUG_FETCH", "Menampilkan data film: ${movie.title}")
                }
            }
        }

        is Resource.Error -> {
            Log.e("DEBUG_FETCH", "Terjadi kesalahan: ${moviesState.message}")
            Text(
                text = "Terjadi kesalahan: ${moviesState.message}",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                color = Color.Red
            )
        }
    }
}
