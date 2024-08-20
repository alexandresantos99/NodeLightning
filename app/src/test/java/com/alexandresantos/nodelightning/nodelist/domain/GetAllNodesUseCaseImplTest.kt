package com.alexandresantos.nodelightning.nodelist.domain

import com.alexandresantos.nodelightning.features.nodelist.data.models.BrType
import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryInterface
import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseImpl
import com.alexandresantos.nodelightning.features.nodelist.domain.GetAllNodesUseCaseInterface
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAllNodesUseCaseImplTest {

    private val repository = mockk<NodeListRepositoryInterface>()

    private val useCase: GetAllNodesUseCaseInterface = GetAllNodesUseCaseImpl(repository)


    @Test
    fun `given result success when calls getAllNodes then should returns list of object`() =
        runTest {
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
            coEvery { repository.getAllNodes() } returns Result.success(expected)
            val result = useCase.getAllNodes()
            assertEquals(Result.success(expected), result)
        }

    @Test
    fun `given result failure when calls getAllNodes then should returns exception`() = runTest {
        val exception = Exception("error")
        coEvery { repository.getAllNodes() } returns Result.failure(exception)
        val result = useCase.getAllNodes()
        assertEquals(Result.failure<Exception>(exception), result)
    }
}