package com.example.shoppinglist.domain

class RemoveShoppingItemUseCase(private val shoppingRepository: ShoppingListRepository) {
    suspend fun removeShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.removeShoppingItem(shoppingItem)
    }
}