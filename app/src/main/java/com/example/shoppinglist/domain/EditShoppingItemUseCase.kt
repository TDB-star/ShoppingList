package com.example.shoppinglist.domain

class EditShoppingItemUseCase(private val shoppingRepository: ShoppinListRepository) {

    fun editShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.editShoppingItem(shoppingItem)
    }
}