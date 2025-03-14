package com.aantrvn.expert1.di

import com.aantrvn.expert1.core.domain.usecase.MovieInteractor
import com.aantrvn.expert1.core.domain.usecase.MoviesUseCase
import com.aantrvn.expert1.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MovieInteractor(get()) }

}

val viewModelModule = module {
    viewModel{ HomeViewModel(get()) }
//    viewModel{ DetailTourismViewModel(get()) }
//    viewModel{ FavoriteViewModel(get()) }
}