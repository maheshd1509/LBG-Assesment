package com.example.domain

import com.example.common.UiState
import com.example.lbgassesment.data.model.ProductDetail
import com.example.lbgassesment.data.respository.RepositoryImpl
import com.example.lbgassesment.domain.usecase.GetProductDetailUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@kotlinx.coroutines.ExperimentalCoroutinesApi
class GetProductDetailUseCaseTest {

    @org.mockito.Mock
    lateinit var repositoryImpl: RepositoryImpl

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Before
    fun setup() {
        org.mockito.MockitoAnnotations.openMocks(this)
        getProductDetailUseCase =
            GetProductDetailUseCase(repositoryImpl)
    }

    @Test
    fun `test getProductDetail returns Success UiState`() =
        runBlockingTest {
            val mockProductDetail = ProductDetail(
                id = 1,
                title = "Product 1",
                category = "category",
                description = "Detailed description",
                price = "100",
                image = "http://example.com/product1.jpg"
            )

            `when`(repositoryImpl.getProductDetail("1")).thenReturn(mockProductDetail)

            val result = getProductDetailUseCase("1").toList()
            assertEquals(3, result.size)
            assert(result[0] is UiState.Loading)
            assert(result[1] is UiState.Success)
            assertEquals(mockProductDetail, (result[1] as UiState.Success).data)
        }

    @Test
    fun `test getProductDetail returns Error UiState when exception occurs`() = runBlockingTest {
        val errorMessage = "Product not found"

        `when`(repositoryImpl.getProductDetail("1")).thenThrow(Exception(errorMessage))
        val result = getProductDetailUseCase("1").toList()

        assertEquals(3, result.size)
        assert(result[0] is UiState.Loading)
        assert(result[1] is UiState.Error)
        assertEquals(errorMessage, (result[1] as UiState.Error).message)
    }
}
