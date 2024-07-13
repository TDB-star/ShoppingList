package com.example.shoppinglist.domain

class GetShoppingListUseCase(private val shoppingRepository: ShoppinListRepository) {

    fun getShoppingList() : List<ShoppingItem> {
        return shoppingRepository.getShoppingList()
    }
}