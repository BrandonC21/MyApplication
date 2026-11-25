package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ProductoFarmacia
import com.example.myapplication.domain.repository.ProductoRepository

class GetProductosFarmaciaUseCase(
    private val repository: ProductoRepository
){
    suspend operator fun invoke(query: String): Result<List<ProductoFarmacia>>{
        return repository.get(query.trim())
    }
}