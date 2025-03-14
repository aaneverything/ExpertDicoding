package com.aantrvn.expert1.ui.home

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aantrvn.expert1.core.data.Resource
import com.aantrvn.expert1.core.domain.model.Movies
import com.aantrvn.expert1.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieUseCase: MoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<Resource<List<Movies>>>(Resource.Loading())
    val movies: StateFlow<Resource<List<Movies>>> = _movies
    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieUseCase.getAllMovies().collect { result ->
                _movies.value = result
            }
        }
    }

}
