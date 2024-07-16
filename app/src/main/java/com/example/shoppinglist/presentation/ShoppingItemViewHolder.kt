package com.example.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R


class ShoppingItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textViewItemName = view.findViewById<TextView>(R.id.textView_item_name)
    val textViewItemCount = view.findViewById<TextView>(R.id.textView_item_count)
}