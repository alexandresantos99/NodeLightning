package com.alexandresantos.nodelightning.features.di

import com.alexandresantos.nodelightning.features.retrofit.RetrofitService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { providesRetrofitApi() }
}

private fun providesRetrofitApi() = Retrofit.Builder()
    .baseUrl("https://mempool.space/api/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(RetrofitService::class.java)