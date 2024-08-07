package com.example.shoppinglist.domain

class EditShoppingItemUseCase(private val shoppingRepository: ShoppingListRepository) {

    suspend fun editShoppingItem(shoppingItem: ShoppingItem) {
        shoppingRepository.editShoppingItem(shoppingItem)
    }
}