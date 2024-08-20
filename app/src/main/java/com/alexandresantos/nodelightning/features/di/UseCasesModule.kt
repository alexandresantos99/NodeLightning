package com.alexandresantos.nodelightning.features.di

import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseImpl
import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseInterface
import org.koin.dsl.module

val useCasesModule = module {
    single<GetAllNodesUseCaseInterface> { GetAllNodesUseCaseImpl(get()) }
}