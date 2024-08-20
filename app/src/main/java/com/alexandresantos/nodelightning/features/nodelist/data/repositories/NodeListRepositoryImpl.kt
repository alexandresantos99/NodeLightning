package com.alexandresantos.nodelightning.features.nodelist.data.repositories

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.retrofit.RetrofitService

class NodeListRepositoryImpl(private val retrofit: RetrofitService) : NodeListRepositoryInterface {
    override suspend fun getAllNodes(): Result<List<NodeListResponse>> {
        return try {
            val nodes = retrofit.getAllNodes()
            Result.success(nodes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}