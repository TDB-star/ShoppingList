package com.example.shoppinglist.domain

class AddShoppingItemUseCase(private val shoppingRepository: ShoppingListRepository) {
    suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.addShoppingItem(shoppingItem)
    }
}