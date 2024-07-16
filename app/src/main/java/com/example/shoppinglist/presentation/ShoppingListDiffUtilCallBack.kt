package com.example.shoppinglist.presentation

import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListDiffUtilCallBack(
    private val oldShoppingList: List<ShoppingItem>,
    private val newShoppingList: List<ShoppingItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldShoppingList.size
    }

    override fun getNewListSize(): Int {
        return newShoppingList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldShoppingList[oldItemPosition]
        val newItem = newShoppingList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldShoppingList[oldItemPosition]
        val newItem = newShoppingList[newItemPosition]
        return oldItem == newItem
    }
}