package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShoppinListRepository
import com.example.shoppinglist.domain.ShoppingItem
import kotlin.random.Random

object ShoppingListRepositoryImpl: ShoppinListRepository {

    private val shoppingListLiveData = MutableLiveData<List<ShoppingItem>>()
    private val shoppingList = sortedSetOf<ShoppingItem>({ o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementedId = 0

    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shoppingListLiveData
    }

    private fun updateList() {
        shoppingListLiveData.value = shoppingList.toList()
    }

    init {
        for (i in 0..20) {
            val item = ShoppingItem("Name $i", i, Random.nextBoolean())
            addShoppingItem(item)
        }
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
        updateList()
    }

    override fun removeShoppingItem(shoppingItem: ShoppingItem) {
        shoppingList.remove(shoppingItem)
        updateList()
    }

    override fun editShoppingItem(shoppingItem: ShoppingItem) {
        val editableItem = getShoppingItem(shoppingItem.id)
        shoppingList.remove(editableItem)
        addShoppingItem(shoppingItem)
    }
}