package com.example.shoppinglist.domain

class GetShoppingItemUseCase(private val shoppingRepository: ShoppinListRepository) {
    fun getShoppingItem(itemId: Int): ShoppingItem {
        return shoppingRepository.getShoppingItem(itemId)
    }
}