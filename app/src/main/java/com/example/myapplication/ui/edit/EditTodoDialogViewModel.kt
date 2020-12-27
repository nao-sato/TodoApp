package com.example.myapplication.ui.edit

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoApplication.Companion.applicationContext
import com.example.myapplication.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class EditTodoDialogViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val contents = MutableLiveData<String>()

    val errorMessage = MutableLiveData<String>()

    var id = 0

    private val todoRepository = TodoRepository(TodoApplication.database.todoDao())

    fun initData() {
        if (id == 0) {
            errorMessage.postValue(applicationContext.getString(R.string.error))
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val todo = todoRepository.loadById(id) ?: return@launch {
                errorMessage.postValue(applicationContext.getString(R.string.error))
            }()
            title.postValue(todo.title)
            contents.postValue(todo.contents)
        }
    }

    fun updateTodo(title: String, contents: String, onCompleteUpdate: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateTodo(id, title, contents)
            onCompleteUpdate.invoke()
        }
    }
}