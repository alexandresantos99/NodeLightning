package com.alexandresantos.nodelightning.features.retrofit

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import retrofit2.http.GET

interface RetrofitService {

    @GET("lightning/nodes/rankings/connectivity")
    suspend fun getAllNodes(): List<NodeListResponse>

}