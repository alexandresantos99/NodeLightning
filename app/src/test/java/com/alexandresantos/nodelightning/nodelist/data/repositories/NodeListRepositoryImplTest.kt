package com.alexandresantos.nodelightning.nodelist.data.repositories

import com.alexandresantos.nodelightning.features.nodelist.data.models.BrType
import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryImpl
import com.alexandresantos.nodelightning.features.nodelist.data.repositories.NodeListRepositoryInterface
import com.alexandresantos.nodelightning.features.retrofit.RetrofitService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NodeListRepositoryImplTest {

    private val retrofit = mockk<RetrofitService>()

    private val repository: NodeListRepositoryInterface =
        NodeListRepositoryImpl(retrofit = retrofit)

    @Test
    fun `given success when calls api then should returns list of object`() = runTest {
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
        coEvery { retrofit.getAllNodes() } returns expected
        val result = repository.getAllNodes()
        assertEquals(Result.success(expected), result)
    }

    @Test
    fun `given error when calls api then should returns failure`() = runTest {
        val exception = Exception("error")
        coEvery { retrofit.getAllNodes() } throws exception
        val result = repository.getAllNodes()
        assertEquals(Result.failure<Exception>(exception), result)
    }
}