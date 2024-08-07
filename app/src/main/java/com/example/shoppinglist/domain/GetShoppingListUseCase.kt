package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShoppingListUseCase(private val shoppingRepository: ShoppingListRepository) {

    fun getShoppingList() : LiveData<List<ShoppingItem>> {
        return shoppingRepository.getShoppingList()
    }
}