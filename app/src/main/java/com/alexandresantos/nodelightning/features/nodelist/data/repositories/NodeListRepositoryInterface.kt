package com.alexandresantos.nodelightning.features.nodelist.data.repositories

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse

interface NodeListRepositoryInterface {

    suspend fun getAllNodes() : Result<List<NodeListResponse>>
}