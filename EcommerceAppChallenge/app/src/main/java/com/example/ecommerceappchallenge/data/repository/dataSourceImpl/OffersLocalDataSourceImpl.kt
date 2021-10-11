package com.example.ecommerceappchallenge.data.repository.dataSourceImpl

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.local.OfferDAO
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.repository.dataSource.OffersLocalDataSource

class OffersLocalDataSourceImpl(private val dao: OfferDAO): OffersLocalDataSource {
    override suspend fun insertOffer(productsItem: ProductsItem) {
        return dao.insertOffer(productsItem)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override fun getOffer(): LiveData<ProductsItem> {
        return dao.getOffer()
    }
}