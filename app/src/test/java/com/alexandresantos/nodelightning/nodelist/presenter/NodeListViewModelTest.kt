package com.alexandresantos.nodelightning.nodelist.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexandresantos.nodelightning.features.nodelist.data.models.BrType
import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseInterface
import com.alexandresantos.nodelightning.features.nodelist.presenter.state.NodeListState
import com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel.NodeListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NodeListViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<GetAllNodesUseCaseInterface>()
    private lateinit var viewModel: NodeListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NodeListViewModel(useCase)
    }

    @After
    fun tearDown() {
        testDispatcher.cancel()
    }


    @Test
    fun `given returns success when calls getAllNodes then should return success state`() = runTest {
        val expected = listOf(
            NodeListResponse(
                publicKey = "public",
                alias = "alias",
                channels = 2548,
                capacity = 62240689513,
                firstSeen = 62240689513,
                updatedAt = 62240689513,
                city = BrType(ptBr = null)
            )
        )

        coEvery { useCase.getAllNodes() } returns Result.success(expected)

        viewModel.getAllNodes()
        advanceUntilIdle()
        assertEquals(NodeListState.Success(data = expected), viewModel.nodesLiveData.value)
    }

    @Test
    fun `given returns exception when calls getAllNodes then should return error state`() = runTest {
        val ex = Exception("error")

        coEvery { useCase.getAllNodes() } returns Result.failure(ex)

        viewModel.getAllNodes()
        advanceUntilIdle()
        assertEquals(NodeListState.Error, viewModel.nodesLiveData.value)
    }
}