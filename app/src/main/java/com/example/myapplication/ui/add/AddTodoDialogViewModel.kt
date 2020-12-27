package com.example.myapplication.ui.add

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTodoDialogViewModel:ViewModel() {

    private val todoRepository: TodoRepository = TodoRepository(database.todoDao())


}
