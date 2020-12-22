package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.add.AddTodoDialogFragment
import com.example.myapplication.ui.edit.EditTodoDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections.addAll

class TodoListAdapter(
        private val layoutInflater: LayoutInflater,
): RecyclerView.Adapter<TodoListAdapter.TodoHolder>() {
    private val TodoList =  mutableListOf<Todo>()

    fun refresh(list:List<Todo>) {
        TodoList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }


    var onClickEditTodo: ((Todo) -> Unit)? = null

    override fun getItemCount() = TodoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = RowBinding.inflate(layoutInflater, parent, false)
        return TodoHolder(binding, onClickEditTodo)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(TodoList[position])
    }

    class TodoHolder(
            private val binding: RowBinding,
            private val onClickEditTodo: ((Todo) -> Unit)?,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            val todoRepository = TodoRepository(TodoApplication.database.todoDao())


//            binding.txtTit.text = todo.title
//            binding.txtCon.text = todo.contents
//            binding.check.isChecked = returnBool(todo)
//
//            binding.check.setOnClickListener {
//                val falseOrTrue = judgeBool(binding.check.isChecked)
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    todoRepository.updateCheck(todo.id, falseOrTrue)
//                }
//            }

            binding.more.setOnClickListener{
                PopupMenu(itemView.context,it).also { popMenu ->
                    popMenu.menuInflater.inflate(R.menu.pop_menu, popMenu.menu)

                    popMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menuListContextDelete ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    todoRepository.deleteTodo(todo)
                                }
                            R.id.menuListContextEdit ->
                                openEditDialog(todo)
                        }
                        return@setOnMenuItemClickListener true
                    }
                } .show()
            }
        }

        private fun openEditDialog(todo: Todo){
            onClickEditTodo?.invoke(todo)

        }

        private fun judgeBool(isChecked: Boolean): Int {
            return if (isChecked == true) {
                1
            } else {
                0
            }
        }

        private fun returnBool(todo: Todo):Boolean{
           return when(todo.checked){
               1 -> true
               else -> false
           }
        }
    }
}
