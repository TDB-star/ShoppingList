package com.example.shoppinglist.domain

interface ShoppinListRepository {

    fun getShoppingList() : List<ShoppingItem>
    fun getShoppingItem(itemId: Int): ShoppingItem
    fun addShoppingItem(shoppingItem: ShoppingItem)
    fun removeShoppingItem(shoppingItem: ShoppingItem)
    fun editShoppingItem(shoppingItem: ShoppingItem)

}