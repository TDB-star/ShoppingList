package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_items")
    fun getShoppingList(): LiveData<List<ShoppingItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShoppingItem(shoppingItem: ShoppingItemDbModel)

    @Query("DELETE FROM shopping_items WHERE id=:id")
    suspend fun deleteShoppingItem(id: Int)

    @Query("SELECT * FROM shopping_items WHERE id=:id LIMIT 1" )
    suspend fun getShoppingItem(id: Int): ShoppingItemDbModel
}