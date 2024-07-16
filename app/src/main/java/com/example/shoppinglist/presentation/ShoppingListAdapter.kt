package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListAdapter: RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder>() {

    var shoppinglist = listOf<ShoppingItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onShoppingItemLongClickListener: ((ShoppingItem) -> Unit)? = null
    var onShoppingItemClickListener: ((ShoppingItem) -> Unit)? = null

    override fun getItemCount(): Int {
        return shoppinglist.size
    }

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
        val item = shoppinglist[position]


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

    override fun onViewRecycled(holder: ShoppingItemViewHolder) {
        super.onViewRecycled(holder)
        holder.textViewItemName.text = "New Item"
        holder.textViewItemCount.text = "0"
    }

    override fun getItemViewType(position: Int): Int {
        val item = shoppinglist[position]
        return if(item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class ShoppingItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewItemName = view.findViewById<TextView>(R.id.textView_item_name)
        val textViewItemCount = view.findViewById<TextView>(R.id.textView_item_count)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}