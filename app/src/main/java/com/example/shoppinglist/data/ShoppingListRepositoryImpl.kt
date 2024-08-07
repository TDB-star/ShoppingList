package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shoppinglist.domain.ShoppingListRepository
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListRepositoryImpl(
    application: Application
) : ShoppingListRepository {

    private val shoppingListDao = AppDatabase.getInstance(application).shoppingListDao()
    private val mapper = ShoppingListMapper()

//    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
//        return MediatorLiveData<List<ShoppingItem>>().apply {
//            addSource(shoppingListDao.getShoppingList()) {
//                value = mapper.mapListDbModelToListEntity(it)
//            }
//        }
//    }

    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shoppingListDao.getShoppingList().map {
            mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun getShoppingItem(itemId: Int): ShoppingItem {
        val dbModel = shoppingListDao.getShoppingItem(itemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
      shoppingListDao.addShoppingItem(mapper.mapEntityToDbModel(shoppingItem))
    }

    override suspend fun removeShoppingItem(shoppingItem: ShoppingItem) {
      shoppingListDao.deleteShoppingItem(shoppingItem.id)
    }

    override suspend fun editShoppingItem(shoppingItem: ShoppingItem) {
        shoppingListDao.addShoppingItem(mapper.mapEntityToDbModel(shoppingItem))
    }
}