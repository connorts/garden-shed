package com.example.ecommerceappchallenge.domain.repository

import com.example.ecommerceappchallenge.data.model.Products
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.model.Rating
import com.example.ecommerceappchallenge.data.util.Resource

class FakeProductsRepository: ProductRepository {
    private val products = Products()

    init {
        products.add(ProductsItem("men's clothing","desc1",1,"img1",1.00, Rating(1.00,1),"title1"))
        products.add(ProductsItem("women's clothing","desc2",2,"img2",2.00, Rating(2.00,2),"title2"))
        products.add(ProductsItem("jewelery","desc3",3,"img3",3.00, Rating(3.00,3),"title3"))
        products.add(ProductsItem("electronics","desc4",4,"img4",4.00, Rating(4.00,4),"title4"))
    }

    override suspend fun getProducts(): Resource<Products> {
        return Resource.Success(products)
    }

    override suspend fun getProductsByCategory(category: String): Resource<Products> {
//        val filteredProducts = products.filter { it.category == category } as Products
//        val filteredProducts = products.apply { filter { it.category==category } }
        products.clear()
        when (category) {
            "men's clothing" -> products.add(ProductsItem("men's clothing","desc1",1,"img1",1.00, Rating(1.00,1),"title1"))
            "women's clothing" -> products.add(ProductsItem("women's clothing","desc2",2,"img2",2.00, Rating(2.00,2),"title2"))
            "jewelery" -> products.add(ProductsItem("jewelery","desc3",3,"img3",3.00, Rating(3.00,3),"title3"))
            "electronics" -> products.add(ProductsItem("electronics","desc4",4,"img4",4.00, Rating(4.00,4),"title4"))
        }
        return Resource.Success(products)
    }

    override suspend fun getRandomProduct(): Resource<ProductsItem> {
        return Resource.Success(products[0])
    }
}