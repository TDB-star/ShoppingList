package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.EditShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingListUseCase
import com.example.shoppinglist.domain.RemoveShoppingItemUseCase
import com.example.shoppinglist.domain.ShoppinListRepository
import com.example.shoppinglist.domain.ShoppingItem

class MainViewModel: ViewModel() {

    private val repository = ShoppingListRepositoryImpl

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val removeShoppingItemUseCase = RemoveShoppingItemUseCase(repository)
    private val editShoppingItemUseCase = EditShoppingItemUseCase(repository)

    val shoppingList = MutableLiveData<List<ShoppingItem>>()

    fun getShoppingList() {
        val list = getShoppingListUseCase.getShoppingList()
        shoppingList.value = list
    }
}