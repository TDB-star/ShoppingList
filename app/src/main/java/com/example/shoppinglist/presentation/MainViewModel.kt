package com.example.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.EditShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingListUseCase
import com.example.shoppinglist.domain.RemoveShoppingItemUseCase
import com.example.shoppinglist.domain.ShoppingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShoppingListRepositoryImpl(application)

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val removeShoppingItemUseCase = RemoveShoppingItemUseCase(repository)
    private val editShoppingItemUseCase = EditShoppingItemUseCase(repository)

    val shoppingList = getShoppingListUseCase.getShoppingList()

    fun deleteShoppingItem(item: ShoppingItem) {
        viewModelScope.launch {
            removeShoppingItemUseCase.removeShoppingItem(item)
        }
    }

    fun enableState(item: ShoppingItem) {

        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editShoppingItemUseCase.editShoppingItem(newItem)
        }

    }
}