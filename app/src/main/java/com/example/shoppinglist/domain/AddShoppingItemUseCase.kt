package com.example.shoppinglist.domain

class AddShoppingItemUseCase(private val shoppingRepository: ShoppinListRepository) {
    fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.addShoppingItem(shoppingItem)
    }
}