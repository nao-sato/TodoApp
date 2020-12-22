package com.example.myapplication.ui

import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private var todoList = mutableListOf<Todo>()
    private var todoRepository: TodoRepository = TodoRepository(TodoApplication.database.todoDao())
    var adapter = TodoListAdapter(LayoutInflater.from(TodoApplication.applicationContext))
    var layout = LinearLayoutManager(TodoApplication.applicationContext)
    //val observeData  = MutableLiveData<MutableList<Todo>>()


    fun loadAllTodo() {
        CoroutineScope(Dispatchers.IO).launch {
            todoList = todoRepository.load()
          //  observeData.postValue(todoList)
            withContext(Dispatchers.Main){
                adapter.refresh(todoList)
            }
        }
    }

    fun loadDoneTodo(){
        CoroutineScope(Dispatchers.IO).launch {
            todoList = todoRepository.load()
            withContext(Dispatchers.Main) {
                val doneList: MutableList<Todo> = todoList.filter { it.checked == 1 } as MutableList<Todo>
                adapter.refresh(doneList)
               // observeData.postValue(doneList)
            }
        }
    }

    fun loadUndoneTodo(){
        CoroutineScope(Dispatchers.IO).launch {
            todoList = todoRepository.load()
            withContext(Dispatchers.Main) {
                val undoneList: MutableList<Todo> = todoList.filter { it.checked == 0 } as MutableList<Todo>
                adapter.refresh(undoneList)
               // observeData.postValue(undoneList)
            }
        }
    }
}