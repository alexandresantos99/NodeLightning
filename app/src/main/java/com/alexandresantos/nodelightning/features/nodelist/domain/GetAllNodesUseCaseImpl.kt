package com.alexandresantos.nodelightning.features.nodelist.domain

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryInterface

class GetAllNodesUseCaseImpl(private val repository: NodeListRepositoryInterface) :
    GetAllNodesUseCaseInterface {
    override suspend fun getAllNodes(): Result<List<NodeListResponse>> = repository.getAllNodes()
}