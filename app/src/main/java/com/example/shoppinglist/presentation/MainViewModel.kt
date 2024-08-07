package com.example.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.EditShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingListUseCase
import com.example.shoppinglist.domain.RemoveShoppingItemUseCase
import com.example.shoppinglist.domain.ShoppingItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShoppingListRepositoryImpl(application)

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val removeShoppingItemUseCase = RemoveShoppingItemUseCase(repository)
    private val editShoppingItemUseCase = EditShoppingItemUseCase(repository)

    val shoppingList = getShoppingListUseCase.getShoppingList()


    fun deleteShoppingItem(item: ShoppingItem) {
        removeShoppingItemUseCase.removeShoppingItem(item)
    }

    fun enableState(item: ShoppingItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editShoppingItemUseCase.editShoppingItem(newItem)
    }
}