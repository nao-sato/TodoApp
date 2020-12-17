package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentAllBinding
import com.example.myapplication.databinding.FragmentAllBindingImpl
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityViewModel : ViewModel() {

    private var todoRepository: TodoRepository = TodoRepository(TodoApplication.database.todoDao())

    var todoList = mutableListOf<Todo>()


    val adapter = TodoListAdapter(todoList, LayoutInflater.from(TodoApplication.applicationContext))
    val layout = LinearLayoutManager(TodoApplication.applicationContext)


    fun loadTodo() {
        CoroutineScope(Dispatchers.IO).launch {
            val data: MutableList<Todo> = todoRepository.load()
            todoList.addAll(data)
            reloadTodoList()
        }
   }

    private suspend fun reloadTodoList() = withContext(Dispatchers.Main){
        adapter.notifyDataSetChanged()
    }
}