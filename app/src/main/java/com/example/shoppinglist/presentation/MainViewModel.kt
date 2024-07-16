package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.EditShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingListUseCase
import com.example.shoppinglist.domain.RemoveShoppingItemUseCase
import com.example.shoppinglist.domain.ShoppinListRepository
import com.example.shoppinglist.domain.ShoppingItem

class MainViewModel : ViewModel() {

    private val repository = ShoppingListRepositoryImpl

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val removeShoppingItemUseCase = RemoveShoppingItemUseCase(repository)
    private val editShoppingItemUseCase = EditShoppingItemUseCase(repository)
    private val getShoppingItemUseCase = GetShoppingItemUseCase(repository)

    val shoppingList = getShoppingListUseCase.getShoppingList()

    fun getShoppingItem(itemId: Int) {
        getShoppingItemUseCase.getShoppingItem(itemId)
    }

    fun deleteShoppingItem(item: ShoppingItem) {
        removeShoppingItemUseCase.removeShoppingItem(item)
    }

    fun enableState(item: ShoppingItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editShoppingItemUseCase.editShoppingItem(newItem)
    }
}