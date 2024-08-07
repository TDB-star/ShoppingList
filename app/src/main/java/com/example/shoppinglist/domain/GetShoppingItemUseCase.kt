package com.example.shoppinglist.domain

class GetShoppingItemUseCase(private val shoppingRepository: ShoppingListRepository) {
    suspend fun getShoppingItem(itemId: Int): ShoppingItem {
        return shoppingRepository.getShoppingItem(itemId)
    }
}