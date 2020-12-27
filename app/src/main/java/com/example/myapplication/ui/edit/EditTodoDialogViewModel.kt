package com.example.myapplication.ui.edit

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoDialogViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val contents = MutableLiveData<String>()

    var id = 0

    private val todoRepository = TodoRepository(TodoApplication.database.todoDao())

    fun initEditData(bundle: Bundle?) {
        id = bundle?.getInt("id") ?: 0
        title.postValue(bundle?.getString("title") ?: "")
        contents.postValue(bundle?.getString("contents") ?: "")
    }

    fun updateTodo(title: String, contents: String) {
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateTodo(id, title, contents)
        }

    }
}