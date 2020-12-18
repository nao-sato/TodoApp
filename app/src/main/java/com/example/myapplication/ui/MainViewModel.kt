package com.example.myapplication.ui

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.todo.DoneToDoFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    var todoRepository: TodoRepository = TodoRepository(TodoApplication.database.todoDao())
    var todoList = mutableListOf<Todo>()

    val adapter = TodoListAdapter(todoList, LayoutInflater.from(TodoApplication.applicationContext))
    val layout = LinearLayoutManager(TodoApplication.applicationContext)

    fun loadAllTodo() {
        CoroutineScope(Dispatchers.IO).launch {
            val data: MutableList<Todo> = todoRepository.load()
            todoList.addAll(data)
            reloadTodoList()
        }
    }

    fun loadDoneTodo(){
        CoroutineScope(Dispatchers.IO).launch {
            val data: MutableList<Todo> = todoRepository.load()
            val done = data.filter { it.checked == 1 }
            todoList.addAll(done)
            reloadTodoList()
        }
    }

    fun loadUndoneTodo(){
        CoroutineScope(Dispatchers.IO).launch {
            val data: MutableList<Todo> = todoRepository.load()
            val undone = data.filter { it.checked == 0}
            todoList.addAll(undone)
            reloadTodoList()
        }
    }

    private suspend fun reloadTodoList() = withContext(Dispatchers.Main){
        adapter.notifyDataSetChanged()
    }



}