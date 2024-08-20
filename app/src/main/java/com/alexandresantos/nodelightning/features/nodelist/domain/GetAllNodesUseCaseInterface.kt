package com.alexandresantos.nodelightning.features.nodelist.domain

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse

interface GetAllNodesUseCaseInterface {
    suspend fun getAllNodes(): Result<List<NodeListResponse>>
}