package com.example.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.AddShoppingItemUseCase
import com.example.shoppinglist.domain.EditShoppingItemUseCase
import com.example.shoppinglist.domain.GetShoppingItemUseCase
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingItemViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShoppingListRepositoryImpl(application)

    private val editShoppingItemUseCase = EditShoppingItemUseCase(repository)
    private val getShoppingItemUseCase = GetShoppingItemUseCase(repository)
    private val addShoppingItemUseCase = AddShoppingItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName


    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shoppingItem = MutableLiveData<ShoppingItem>()
    val shoppingItem: LiveData<ShoppingItem>
        get() = _shoppingItem


    private val _onShoppingListActivity = MutableLiveData<Unit>()
    val onShoppingListActivity: LiveData<Unit>
        get() = _onShoppingListActivity

    fun getShoppingItem(itemId: Int) {
        val item = getShoppingItemUseCase.getShoppingItem(itemId)
        _shoppingItem.value = item
    }

    fun addShoppingItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parsCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if(fieldsValid) {
            val newItem = ShoppingItem(name, count, true)
            addShoppingItemUseCase.addShoppingItem(newItem)
            onCloseScreen()
        }
    }

    fun editShoppingItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parsCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if(fieldsValid) {
            _shoppingItem.value?.let {
                val newItem = it.copy(name = name, count = count)
                editShoppingItemUseCase.editShoppingItem(newItem)
                onCloseScreen()
            }
        }
    }

   private fun parseName(inputName: String?) : String {
        return inputName?.trim() ?: ""
    }

    private fun parsCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int) : Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if(count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun onCloseScreen() {
        _onShoppingListActivity.value = Unit
    }
}