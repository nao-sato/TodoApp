package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val items = MutableLiveData<List<Todo>>()

    fun updateData() {
        CoroutineScope(Dispatchers.IO).launch {
            items.postValue(database.todoDao().loadAllTodo())
        }
    }
}