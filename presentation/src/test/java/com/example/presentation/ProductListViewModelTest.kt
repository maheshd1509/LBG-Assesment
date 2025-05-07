package com.example.presentation

import com.example.common.UiState
import com.example.lbgassesment.data.model.ProductItem
import com.example.lbgassesment.domain.usecase.GetProductListUseCase
import com.example.lbgassesment.presentation.state.ProductListState
import com.example.lbgassesment.presentation.viewmodel.ProductListVewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    @Mock
    lateinit var getProductListUseCase: GetProductListUseCase
    private lateinit var viewModel: ProductListVewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProductListVewModel(getProductListUseCase)
    }

    @Test
    fun `test product list fetch success`() = runBlockingTest {
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

        `when`(getProductListUseCase.invoke()).thenReturn(
            flow { emit(UiState.Success(mockProductList)) }
        )
        viewModel.productList.value
        assertEquals(ProductListState(data = mockProductList), viewModel.productList.value)
    }

    @Test
    fun `test product list fetch error`() = runBlockingTest {
        val errorMessage = "Network error"
        `when`(getProductListUseCase.invoke()).thenReturn(
            flow { emit(UiState.Error(message = errorMessage)) }
        )

        viewModel.productList.value
        assertEquals(ProductListState(error = errorMessage), viewModel.productList.value)
    }

    @Test
    fun `test product list fetch loading state`() = runBlockingTest {
        `when`(getProductListUseCase.invoke()).thenReturn(
            flow { emit(UiState.Loading()) }
        )
        viewModel.productList.value
        assertEquals(ProductListState(isLoading = true), viewModel.productList.value)
    }
}
