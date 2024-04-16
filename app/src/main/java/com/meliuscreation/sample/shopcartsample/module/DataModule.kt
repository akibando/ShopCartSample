package com.meliuscreation.sample.shopcartsample.module

import android.content.Context
import com.meliuscreation.sample.shopcartsample.data.api.DummyWebApi
import com.meliuscreation.sample.shopcartsample.data.api.ProductApi
import com.meliuscreation.sample.shopcartsample.data.db.AppDatabase
import com.meliuscreation.sample.shopcartsample.data.db.ProductDao
import com.meliuscreation.sample.shopcartsample.data.repository.DummyWebApiProductDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.DummyWebImageDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ImageDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ProductDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepositoryImpl
import com.meliuscreation.sample.shopcartsample.data.repository.RoomProductDataSource
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideProductRepository(
        localDataSource: ProductDataSource.Local,
        remoteDataSource: ProductDataSource.Remote,
        remoteImageDataSource: ImageDataSource.Remote
    ): ProductRepository {
        return ProductRepositoryImpl(localDataSource, remoteDataSource, remoteImageDataSource)
    }
    @Provides
    @Singleton
    fun provideProductDataSourceLocal(
        productDao: ProductDao
    ): ProductDataSource.Local {
        return RoomProductDataSource(productDao)
    }
    @Provides
    @Singleton
    fun provideProductDataSourceRemote(
        api: ProductApi
    ): ProductDataSource.Remote {
        return DummyWebApiProductDataSource(api)
    }
    @Provides
    @Singleton
    fun provideProductDao(@ApplicationContext applicationContext: Context): ProductDao {
        return AppDatabase.getDatabase(applicationContext).productDao()
    }
    @Provides
    @Singleton
    fun provideImageDataRemote(): ImageDataSource.Remote {
        return DummyWebImageDataSource()
    }
    @Provides
    @Singleton
    fun provideProductApi(@ApplicationContext applicationContext: Context): ProductApi {
        return DummyWebApi(applicationContext)
    }
    @Provides
    fun provideAddCartItemUseCase(productRepository: ProductRepository): AddCartItemUseCase {
        return AddCartItemUseCase(productRepository)
    }
    @Provides
    fun provideGetCartItemsUseCase(productRepository: ProductRepository): GetCartItemsUseCase {
        return GetCartItemsUseCase(productRepository)
    }
    @Provides
    fun provideGetProductItemsUseCase(productRepository: ProductRepository): GetProductItemsUseCase {
        return GetProductItemsUseCase(productRepository)
    }
}