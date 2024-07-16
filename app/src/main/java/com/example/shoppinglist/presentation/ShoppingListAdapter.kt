package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListAdapter: ListAdapter<ShoppingItem, ShoppingItemViewHolder>(ShoppingItemDiffCallback()) {

    var onShoppingItemLongClickListener: ((ShoppingItem) -> Unit)? = null
    var onShoppingItemClickListener: ((ShoppingItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = getItem(position)


        holder.itemView.setOnClickListener {
            onShoppingItemClickListener?.invoke(item)
        }

        holder.itemView.setOnLongClickListener {
            onShoppingItemLongClickListener?.invoke(item)
            true
        }

        holder.textViewItemName.text = "${item.name}"
        holder.textViewItemCount.text = item.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}