package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShoppinListRepository
import com.example.shoppinglist.domain.ShoppingItem

object ShoppingListRepositoryImpl: ShoppinListRepository {

    private val shoppingList = mutableListOf<ShoppingItem>()
    private var autoIncrementedId = 0

    override fun getShoppingList(): List<ShoppingItem> {
        return shoppingList.toList()
    }

    override fun getShoppingItem(itemId: Int): ShoppingItem {
        return shoppingList.find {
            it.id == itemId
        } ?: throw
            RuntimeException("Element with id $itemId not found")
    }

    override fun addShoppingItem(shoppingItem: ShoppingItem) {
        if (shoppingItem.id == ShoppingItem.UNDEFINED_ID) {
            shoppingItem.id = autoIncrementedId
            autoIncrementedId++
        }
        shoppingList.add(shoppingItem)
    }

    override fun removeShoppingItem(shoppingItem: ShoppingItem) {
        shoppingList.remove(shoppingItem)
    }

    override fun editShoppingItem(shoppingItem: ShoppingItem) {
        val editableItem = getShoppingItem(shoppingItem.id)
        shoppingList.remove(editableItem)
        shoppingList.add(shoppingItem)
    }
}