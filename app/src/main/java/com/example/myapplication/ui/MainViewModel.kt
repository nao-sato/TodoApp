package com.example.myapplication.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val items = MutableLiveData<List<Todo>>()
    val editTodo = MutableLiveData<Todo>()
    val deleteTodo = MutableLiveData<Todo>()

    private var todoRepository: TodoRepository = TodoRepository(TodoApplication.database.todoDao())

    fun initData() {
        Handler(Looper.getMainLooper()).postDelayed({
           updateData()
        }, 50L)
    }

    fun addTodo(title:String, contents:String){
        CoroutineScope(Dispatchers.IO).launch {
            val t = Todo(title,contents,0)
            todoRepository.insert(t)
        }
        initData()
    }

    fun updateTodoCheck(todo: Todo) {
        val isCurrentCheck = todo.checked == 1
        val willChecked = !isCurrentCheck
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateCheck(todo.id, if (willChecked) 1 else 0)
        }
        initData()
    }

    fun willDelete(todo: Todo) {
        deleteTodo.postValue(todo)
    }

    fun delete(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.deleteTodo(todo)
            initData()
        }
    }

    fun edit(todo: Todo) {
        editTodo.postValue(todo)
    }

    fun updateData() {
        CoroutineScope(Dispatchers.IO).launch {
            items.postValue(todoRepository.load())
        }
    }
}