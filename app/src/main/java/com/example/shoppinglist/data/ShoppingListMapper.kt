package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListMapper {

    fun mapEntityToDbModel(shoppingItem: ShoppingItem): ShoppingItemDbModel {
        return ShoppingItemDbModel(
            id = shoppingItem.id,
            name = shoppingItem.name,
            count = shoppingItem.count,
            enabled = shoppingItem.enabled
        )
    }

    fun mapDbModelToEntity(shoppingItem: ShoppingItemDbModel): ShoppingItem {
        return ShoppingItem(
            id = shoppingItem.id,
            name = shoppingItem.name,
            count = shoppingItem.count,
            enabled = shoppingItem.enabled)
    }

    fun mapListDbModelToListEntity(list: List<ShoppingItemDbModel>): List<ShoppingItem> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}