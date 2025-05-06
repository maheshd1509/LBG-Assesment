package com.example.lbgassesment;

import com.example.lbgassesment.data.UiState
import com.example.lbgassesment.data.respository.RepositoryImpl
import com.example.lbgassesment.domain.model.ProductItem
import com.example.lbgassesment.domain.usecase.GetProductListUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetProductListUseCaseTest {

    @Mock
    lateinit var repositoryImpl: RepositoryImpl

    private lateinit var getProductListUseCase: GetProductListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getProductListUseCase = GetProductListUseCase(repositoryImpl)
    }

    @Test
    fun `test getProductList returns Success UiState`() = runBlockingTest {
        val mockProductList = listOf(
            ProductItem(
                id = 1,
                title = "Product 1",
                description = "Detailed description 1",
                image = "http://example.com/product1.jpg"
            ),
            ProductItem(
                id = 2,
                title = "Product 2",
                description = "Detailed description 2",
                image = "http://example.com/product2.jpg"
            )
        )

        `when`(repositoryImpl.getProductList()).thenReturn(mockProductList)
        val result = getProductListUseCase().toList()

        assertEquals(3, result.size)
        assert(result[0] is UiState.Loading)
        assert(result[1] is UiState.Success)
        assertEquals(mockProductList, (result[1] as UiState.Success).data)
    }

    @Test
    fun `test getProductList returns Error UiState when exception occurs`() = runBlockingTest {
        val errorMessage = "Network error"
        `when`(repositoryImpl.getProductList()).thenThrow(Exception(errorMessage))

        val result = getProductListUseCase().toList()
        assertEquals(3, result.size)
        assert(result[0] is UiState.Loading)
        assert(result[1] is UiState.Error)
        assertEquals(errorMessage, (result[1] as UiState.Error).message)
    }
}
