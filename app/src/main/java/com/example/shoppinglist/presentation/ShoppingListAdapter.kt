package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingItem

class ShoppingListAdapter: RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder>() {

    val Shoppinglist = listOf<ShoppingItem>()

    override fun getItemCount(): Int {
        return Shoppinglist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_enabled, parent, false)

        return ShoppingItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = Shoppinglist[position]

        holder.textViewItemName.text = item.name
        holder.textViewItemCount.text = item.count.toString()

        holder.itemView.setOnLongClickListener {
            true
        }
    }

    class ShoppingItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewItemName = view.findViewById<TextView>(R.id.textView_item_name)
        val textViewItemCount = view.findViewById<TextView>(R.id.textView_item_count)
    }
}