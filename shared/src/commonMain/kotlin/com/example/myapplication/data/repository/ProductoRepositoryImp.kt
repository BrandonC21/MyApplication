package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.controller.ApifyFarmaciaController
import com.example.myapplication.domain.model.ProductoFarmacia
import com.example.myapplication.domain.repository.ProductoRepository

class ProductoRepositoryImp(
    private val controller: ApifyFarmaciaController
): ProductoRepository {
    override suspend fun get(query: String): Result<List<ProductoFarmacia>> {
        return try {
            val items = controller.obtenerProductosFarmacia(query).map { it.toDomain() }
            val reultado = if (query.isBlank()){
                items
            }else{
                items.filter { it.nombreMedicamento.contains(query.trim(), ignoreCase = true) }
            }
            Result.success(reultado)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}