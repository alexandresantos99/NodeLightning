package com.alexandresantos.nodelightning.features.di

import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryImpl
import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryInterface
import org.koin.dsl.module

val repositoriesModule = module {
    single<NodeListRepositoryInterface> { NodeListRepositoryImpl(get()) }
}