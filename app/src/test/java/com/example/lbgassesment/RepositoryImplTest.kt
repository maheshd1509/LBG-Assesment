package com.example.lbgassesment;

import com.example.lbgassesment.data.model.ProductListDTO
import com.example.lbgassesment.data.netwotk.ApiService
import com.example.lbgassesment.data.respository.RepositoryImpl
import com.example.lbgassesment.domain.model.ProductItem
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    @Mock
    lateinit var apiService: ApiService
    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repositoryImpl = RepositoryImpl(apiService)
    }

    @Test
    fun `test getProductList returns list of ProductItem`() = runBlockingTest {
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
        `when`(apiService.getAllProductListAPI()).thenReturn(
            listOf(
                ProductListDTO(
                    id = 1,
                    title = "Product 1",
                    category = "category 1",
                    description = "Detailed description 1",
                    price = "100",
                    image = "http://example.com/product1.jpg"
                ),
                ProductListDTO(
                    id = 2,
                    title = "Product 2",
                    category = "category 2",
                    description = "Detailed description 2",
                    price = "150",
                    image = "http://example.com/product2.jpg"
                )

            )
        )
        val result = repositoryImpl.getProductList()
        assertEquals(mockProductList, result)
        verify(apiService, times(1)).getAllProductListAPI()
    }

    @Test
    fun `test getProductDetail returns ProductDetail`() = runBlockingTest {
        val mockProductDetail = ProductListDTO(
            id = 1,
            category = "category",
            title = "Product 1",
            description = "Detailed description",
            price = "100",
            image = "http://example.com/product1.jpg"
        )

        `when`(apiService.getProductDetailsAPI("1")).thenReturn(
            ProductListDTO(
                id = 1,
                title = "Product 1",
                category = "category",
                description = "Detailed description",
                price = "100",
                image = "http://example.com/product1.jpg"
            )
        )
        val result = repositoryImpl.getProductDetail("1")
        assertEquals(mockProductDetail, result)
        verify(apiService, times(1)).getProductDetailsAPI("1")
    }
}
