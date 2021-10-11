package com.example.ecommerceappchallenge.di

import com.example.ecommerceappchallenge.domain.repository.CartRepository
import com.example.ecommerceappchallenge.domain.repository.OffersRepository
import com.example.ecommerceappchallenge.domain.repository.ProductRepository
import com.example.ecommerceappchallenge.domain.repository.ProfileRepository
import com.example.ecommerceappchallenge.domain.usecase.cart.*
import com.example.ecommerceappchallenge.domain.usecase.offers.DeleteOffersUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.GetDailyDealUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.GetSavedOfferUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.SaveDailyDealUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsByCategoryUseCase
import com.example.ecommerceappchallenge.domain.usecase.products.GetProductsUseCase
import com.example.ecommerceappchallenge.domain.usecase.profile.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(productRepository: ProductRepository): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideDecreaseItemQuantityUseCase(cartRepository: CartRepository): DecreaseItemQuantityUseCase {
        return DecreaseItemQuantityUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCartItemUseCase(cartRepository: CartRepository): DeleteCartItemUseCase {
        return DeleteCartItemUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun providesGetProfileDataUseCase(profileRepository: ProfileRepository): GetProfileDataUseCase {
        return GetProfileDataUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCart(cartRepository: CartRepository): DeleteCartUseCase {
        return DeleteCartUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteProfileUseCase(profileRepository: ProfileRepository): DeleteProfileUseCase {
        return DeleteProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetCartDataUseCase(cartRepository: CartRepository): GetCartDataUseCase {
        return GetCartDataUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideGetCheckoutTotalUseCase(cartRepository: CartRepository): GetCheckoutTotalUseCase {
        return GetCheckoutTotalUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideGetProfileUseCase(profileRepository: ProfileRepository): GetProfileUseCase {
        return GetProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetShoppingCartUseCase(cartRepository: CartRepository): GetShoppingCartUseCase {
        return GetShoppingCartUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideIncreaseItemQuantityUseCase(cartRepository: CartRepository): IncreaseItemQuantityUseCase {
        return IncreaseItemQuantityUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideInsertCartItemUseCase(cartRepository: CartRepository): InsertCartItemUseCase {
        return InsertCartItemUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideInsertProfileUseCase(profileRepository: ProfileRepository): InsertProfileUseCase {
        return InsertProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateEmailUseCase(profileRepository: ProfileRepository): UpdateEmailUseCase {
        return UpdateEmailUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideUpdatePhoneUseCase(profileRepository: ProfileRepository): UpdatePhoneUseCase {
        return UpdatePhoneUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateAddressUseCase(profileRepository: ProfileRepository): UpdateAddressUseCase {
        return UpdateAddressUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetItemQuantityUseCase(cartRepository: CartRepository): GetItemQuantityUseCase {
        return GetItemQuantityUseCase(cartRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductByCategoryUseCase(productRepository: ProductRepository): GetProductsByCategoryUseCase {
        return GetProductsByCategoryUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideUpdatePhotoUseCase(profileRepository: ProfileRepository): UpdatePhotoUseCase {
        return UpdatePhotoUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetDailyDealUseCase(productRepository: ProductRepository): GetDailyDealUseCase {
        return GetDailyDealUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteOffersUseCase(offersRepository: OffersRepository): DeleteOffersUseCase {
        return DeleteOffersUseCase(offersRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedOfferUseCase(offersRepository: OffersRepository): GetSavedOfferUseCase {
        return GetSavedOfferUseCase(offersRepository)
    }

    @Provides
    @Singleton
    fun provideSaveDailyDealUseCase(offersRepository: OffersRepository): SaveDailyDealUseCase {
        return SaveDailyDealUseCase(offersRepository)
    }
}