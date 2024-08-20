package com.alexandresantos.nodelightning.features.nodelist.presenter.state

import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse

sealed class NodeListState {
    data class Success(var data: List<NodeListResponse>) : NodeListState()
    data object Error : NodeListState()
    data object Loading : NodeListState()
}