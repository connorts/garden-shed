package com.example.ecommerceappchallenge.di

import android.app.Application
import com.example.ecommerceappchallenge.domain.usecase.cart.*
import com.example.ecommerceappchallenge.domain.usecase.offers.GetSavedOfferUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsByCategoryUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.*
import com.example.ecommerceappchallenge.viewmodel.cart.CartItemViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.cart.CheckoutActivityViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.main.MainActivityViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.offers.OffersViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.products.ProductsViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.profile.EditProfileViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileViewModelFactory
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileUpdateViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun providesProductsViewModelFactory(
        app: Application,
        getProductsUseCase: GetProductsUseCase,
        increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
        insertCartItemUseCase: InsertCartItemUseCase,
        getCartDataUseCase: GetCartDataUseCase,
        getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
        dispatcher: CoroutineDispatcher

    ): ProductsViewModelFactory {
        return ProductsViewModelFactory(app,
            getProductsUseCase,
            increaseItemQuantityUseCase,
            insertCartItemUseCase,
            getCartDataUseCase,
            getProductsByCategoryUseCase,
            dispatcher)
    }

    @Singleton
    @Provides
    fun providesMainActivityViewModelFactory(getProfileUseCase: GetProfileUseCase): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(getProfileUseCase)
    }

    @Singleton
    @Provides
    fun providesProfileViewModelFactory(getProfileUseCase: GetProfileUseCase,
                                        updatePhotoUseCase: UpdatePhotoUseCase
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(getProfileUseCase,updatePhotoUseCase)
    }

    @Singleton
    @Provides
    fun providesProfileUpdateViewModelFactory(
        deleteProfileUseCase: DeleteProfileUseCase,
        insertProfileUseCase: InsertProfileUseCase,
        dispatcher: CoroutineDispatcher): ProfileUpdateViewModelFactory {
        return ProfileUpdateViewModelFactory(deleteProfileUseCase,insertProfileUseCase,dispatcher)
    }

    @Provides
    @Singleton
    fun providesCartItemViewModelFactory(
        getShoppingCartUseCase: GetShoppingCartUseCase,
        getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
        getProfileDataUseCase: GetProfileDataUseCase,
        deleteCartItemUseCase: DeleteCartItemUseCase,
        decreaseItemQuantityUseCase: DecreaseItemQuantityUseCase,
        increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
        dispatcher: CoroutineDispatcher

    ): CartItemViewModelFactory {
        return CartItemViewModelFactory(
            getShoppingCartUseCase,
            getCheckoutTotalUseCase,
            getProfileDataUseCase,
            deleteCartItemUseCase,
            decreaseItemQuantityUseCase,
            increaseItemQuantityUseCase,
            dispatcher)
    }

    @Provides
    @Singleton
    fun providesCheckoutActivityViewModelFactory(
        getShoppingCartUseCase: GetShoppingCartUseCase,
        getCheckoutTotalUseCase: GetCheckoutTotalUseCase,
        getProfileUseCase: GetProfileUseCase,
        deleteCartUseCase: DeleteCartUseCase

    ): CheckoutActivityViewModelFactory {
        return CheckoutActivityViewModelFactory(
            getShoppingCartUseCase,
            getCheckoutTotalUseCase,
            getProfileUseCase,
            deleteCartUseCase)
    }

    @Provides
    @Singleton
    fun providesEditProfileActivityViewModelFactory(
        getProfileUseCase: GetProfileUseCase,
        updateEmailUseCase: UpdateEmailUseCase,
        updatePhoneUseCase: UpdatePhoneUseCase,
        updateAddressUseCase: UpdateAddressUseCase,
        dispatcher: CoroutineDispatcher

    ): EditProfileViewModelFactory {
        return EditProfileViewModelFactory(
            getProfileUseCase,
            updateEmailUseCase,
            updatePhoneUseCase,
            updateAddressUseCase,
            dispatcher)
    }

    @Provides
    @Singleton
    fun providesOffersViewModelFactory(insertCartItemUseCase: InsertCartItemUseCase,
                                       getSavedOfferUseCase: GetSavedOfferUseCase,
                                       dispatcher: CoroutineDispatcher): OffersViewModelFactory {
        return OffersViewModelFactory(insertCartItemUseCase,getSavedOfferUseCase,dispatcher)
    }

}