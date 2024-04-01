package com.meliuscreation.sample.shopcartsample.ui

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getProductItemsUseCase: GetProductItemsUseCase,
    private val addProductUseCase: AddCartItemUseCase
) : ViewModel() {

    data class CartItemsUiState(
        val image: Bitmap? = null,
        val title: String = "",

    )

    data class ProductItemsUiState(
        val image: Bitmap? = null,
        val imageUrl: String = "",
        val title: String = "",
        val content: String = "",
        val price: Int = 0,
        val availability: String = ""
    )

    private val _productItemsLiveData = MutableLiveData<List<ProductItemsUiState>>()
    private val _productCartLiveData = MutableLiveData<List<CartItemsUiState>>()

    fun onInitialProductItemsState() {
        viewModelScope.launch {
            val productItems = getProductItemsUseCase()
            _productItemsLiveData.value = productItems.map { item ->
                ProductItemsUiState(
                    image = item.image,
                    imageUrl = item.imageUrl,
                    title = item.name,
                    content = item.content,
                    price = item.price,
                    availability = item.status
                )
            }
        }
    }

    fun getProductItems(): LiveData<List<ProductItemsUiState>> {
        return _productItemsLiveData
    }

    fun onInitialCartItemsState() {
        viewModelScope.launch {
            val cartItems = getCartItemsUseCase()
            _productCartLiveData.value = cartItems.map { item ->
                CartItemsUiState(
                    image = item.image,
                    title = item.name
                )
            }
        }
    }

    fun getCartItems(): LiveData<List<CartItemsUiState>> {
        return _productCartLiveData
    }

    fun getProductItem(title: String): ProductItemsUiState {
        val productItems = _productItemsLiveData.value
        productItems?.let { items ->
            return items.find { it.title == title }!!
        }

        return ProductItemsUiState()
    }

    fun onAddCartItem(productItemsUiState: ProductItemsUiState) {
        viewModelScope.launch {
            addProductUseCase(
                CartItemEntity(
                    imageUrl = productItemsUiState.imageUrl,
                    name = productItemsUiState.title
                )
            )
        }
    }
}