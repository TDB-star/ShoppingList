package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShoppingListRepository {

    fun getShoppingList() : LiveData<List<ShoppingItem>>
    suspend fun getShoppingItem(itemId: Int): ShoppingItem
    suspend fun addShoppingItem(shoppingItem: ShoppingItem)
    suspend fun removeShoppingItem(shoppingItem: ShoppingItem)
    suspend fun editShoppingItem(shoppingItem: ShoppingItem)

}