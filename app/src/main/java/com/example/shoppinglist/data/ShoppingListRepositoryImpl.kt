package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShoppinListRepository
import com.example.shoppinglist.domain.ShoppingItem
import kotlin.random.Random

class ShoppingListRepositoryImpl(
    application: Application
) : ShoppinListRepository {

    private val shoppingListDao = AppDatabase.getInstance(application).shoppingListDao()
    private val mapper = ShoppingListMapper()

    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shoppingListDao.getShoppingList()
    }

    override fun getShoppingItem(itemId: Int): ShoppingItem {
    val dbModel = shoppingListDao.getShoppingItem(itemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun addShoppingItem(shoppingItem: ShoppingItem) {
      shoppingListDao.addShoppingItem(mapper.mapEntityToDbModel(shoppingItem))
    }

    override fun removeShoppingItem(shoppingItem: ShoppingItem) {
      shoppingListDao.deleteShoppingItem(shoppingItem.id)
    }

    override fun editShoppingItem(shoppingItem: ShoppingItem) {
        shoppingListDao.addShoppingItem(mapper.mapEntityToDbModel(shoppingItem))
    }
}