package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShoppinListRepository {

    fun getShoppingList() : LiveData<List<ShoppingItem>>
    fun getShoppingItem(itemId: Int): ShoppingItem
    fun addShoppingItem(shoppingItem: ShoppingItem)
    fun removeShoppingItem(shoppingItem: ShoppingItem)
    fun editShoppingItem(shoppingItem: ShoppingItem)

}