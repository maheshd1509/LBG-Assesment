package com.example.presentation

import com.example.common.UiState
import com.example.lbgassesment.data.model.ProductDetail
import com.example.lbgassesment.domain.usecase.GetProductDetailUseCase
import com.example.lbgassesment.presentation.state.ProductDetailState
import com.example.lbgassesment.presentation.viewmodel.ProductDetailVewModel
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
class ProductDetailViewModelTest {

    @Mock
    lateinit var getProductDetailUseCase: GetProductDetailUseCase

    private lateinit var viewModel: ProductDetailVewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProductDetailVewModel(
            getProductDetailUseCase
        )
    }

    @Test
    fun `test getProductDetail fetch success`() = runBlockingTest {
        val mockProductDetail = ProductDetail(
            id = 1,
            title = "Product 1",
            category = "category",
            description = "Detailed description",
            price = "100",
            image = "http://example.com/product1.jpg"
        )

        `when`(getProductDetailUseCase.invoke("1")).thenReturn(
            flow { emit(UiState.Success(mockProductDetail)) }
        )

        viewModel.getProductDetailAPi("1")
        assertEquals(ProductDetailState(data = mockProductDetail), viewModel.productDetail.value)
    }

    @Test
    fun `test getProductDetail fetch error`() = runBlockingTest {
        val errorMessage = "Product not found"

        `when`(getProductDetailUseCase.invoke("1")).thenReturn(
            flow { emit(UiState.Error(message = errorMessage)) }
        )

        viewModel.getProductDetailAPi("1")
        assertEquals(ProductDetailState(error = errorMessage), viewModel.productDetail.value)
    }

    @Test
    fun `test getProductDetail fetch loading state`() = runBlockingTest {
        `when`(getProductDetailUseCase.invoke("1")).thenReturn(
            flow { emit(UiState.Loading()) }
        )

        viewModel.getProductDetailAPi("1")
        assertEquals(ProductDetailState(isLoading = true), viewModel.productDetail.value)
    }
}
