package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.ProductoFarmacia

interface ProductoRepository {
    suspend fun get(query: String): Result<List<ProductoFarmacia>>
}