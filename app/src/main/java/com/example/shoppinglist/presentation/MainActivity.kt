package com.example.shoppinglist.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shoppingList.observe(this) {
            adapter.submitList(it)
        }

        val addShoppingItemButton = findViewById<FloatingActionButton>(R.id.floatingButtonAddItem)
        addShoppingItemButton.setOnClickListener {
            val intent = ShoppingItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_shopping_list)
        adapter = ShoppingListAdapter()
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShoppingListAdapter.VIEW_TYPE_DISABLED,
            ShoppingListAdapter.MAX_POOL_SIZE
        )
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShoppingListAdapter.VIEW_TYPE_ENABLED,
            ShoppingListAdapter.MAX_POOL_SIZE
        )

        onShoppingItemClick()
        onShoppingItemLongClick()
        deleteItemWithSwipe(recyclerView)

    }

    private fun onShoppingItemClick() {
        adapter.onShoppingItemClickListener = { item -> ShoppingItem
            val intent = ShoppingItemActivity.newIntentEditItem(this, item.id)
            startActivity(intent)
        }
    }

    private fun onShoppingItemLongClick() {
        adapter.onShoppingItemLongClickListener = {
            viewModel.enableState(it)
        }
    }

    private fun deleteItemWithSwipe(recyclerView: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShoppingItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}