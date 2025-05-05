package com.example.lbgassesment.domain.usecase
import com.example.lbgassesment.data.UiState
import com.example.lbgassesment.data.respository.RepositoryImpl
import com.example.lbgassesment.domain.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repositoryImpl : RepositoryImpl)  {

    operator fun invoke(id : String) : Flow<UiState<ProductDetail>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getProductDetail(id)))
        }catch (e : Exception){
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}