package com.example.ecommerceappchallenge.data.repository.impl

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.repository.dataSource.ProductsRemoteDataSource
import com.example.ecommerceappchallenge.data.util.Resource
import com.example.ecommerceappchallenge.domain.repository.ProductRepository
import retrofit2.Response

class ProductRepositoryImpl(private val productsRemoteDataSource: ProductsRemoteDataSource): ProductRepository {

    override suspend fun getProducts(): Resource<Products> {
        return responseProductsToResource(productsRemoteDataSource.getProducts())
    }

    override suspend fun getProductsByCategory(category: String): Resource<Products> {
        return responseProductsToResource(productsRemoteDataSource.getProductsByCategory(category))
    }

    override suspend fun getRandomProduct(): Resource<ProductsItem> {
        return responseProductsItemToResource(productsRemoteDataSource.getSpecifiedProduct((1..20).random()))
    }

    private fun responseProductsToResource(response: Response<Products>): Resource<Products> {
        if (response.isSuccessful) {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseProductsItemToResource(response: Response<ProductsItem>): Resource<ProductsItem> {
        if (response.isSuccessful) {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}