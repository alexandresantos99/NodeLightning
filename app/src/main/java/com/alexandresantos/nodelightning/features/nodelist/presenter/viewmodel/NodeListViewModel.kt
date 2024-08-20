package com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseInterface
import com.alexandresantos.nodelightning.features.nodelist.presenter.state.NodeListState
import kotlinx.coroutines.launch


class NodeListViewModel(private val useCase: GetAllNodesUseCaseInterface) : ViewModel() {

    private val _nodesLiveData = MutableLiveData<NodeListState>().apply {
        value = NodeListState.Loading
    }
    val nodesLiveData: LiveData<NodeListState> get() = _nodesLiveData

    fun getAllNodes() {
        viewModelScope.launch {
            useCase.getAllNodes().onSuccess { list ->
                _nodesLiveData.postValue(NodeListState.Success(data = list))
            }.onFailure {
                _nodesLiveData.postValue(NodeListState.Error)
            }
        }
    }

}