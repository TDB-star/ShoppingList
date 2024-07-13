package com.example.shoppinglist.domain

class RemoveShoppingItemUseCase(private val shoppingRepository: ShoppinListRepository) {
    fun removeShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.removeShoppingItem(shoppingItem)
    }
}