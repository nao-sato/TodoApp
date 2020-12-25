package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosView: RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val customAdapter by lazy { Adapter(context) }

    init {
        adapter = customAdapter
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    class Adapter(private val context: Context): RecyclerView.Adapter<ViewHolder>() {

        private val viewModel: MainViewModel by (context as ComponentActivity).viewModels()
        private val items =  mutableListOf<Todo>()

        fun refresh(list:List<Todo>) {
            items.apply {
                clear()
                addAll(list)
            }
            notifyDataSetChanged()
        }

        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ItemViewHolder(RowBinding.inflate(LayoutInflater.from(context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            when (holder) {
                is ItemViewHolder -> onBindViewHolder(holder, position)
            }
        }

        private fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val data = items[position]
            holder.binding.apply {
                todo = data
                check.setOnClickListener {
                    viewModel.updateTodoCheck(data)
                }
                more.setOnClickListener{
                    PopupMenu(context, it).also { popMenu ->
                        popMenu.menuInflater.inflate(R.menu.pop_menu, popMenu.menu)
                        popMenu.setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.menuListContextDelete -> viewModel.willDelete(data)
                                R.id.menuListContextEdit -> viewModel.edit(data)
                            }
                            return@setOnMenuItemClickListener true
                        }
                    } .show()
                }
            }
        }

        class ItemViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root)
    }
}