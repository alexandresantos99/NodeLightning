package com.alexandresantos.nodelightning.features.di

import com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel.NodeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { NodeListViewModel(get()) }
}