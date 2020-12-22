package com.example.myapplication.ui.edit

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoDialogViewModel : ViewModel() {

    val dialog = EditTodoDialogFragment()
    val bundle: Bundle? = dialog.arguments


    val id = bundle?.getInt("id")
    val title = bundle?.getString("title")
    val contents = bundle?.getString("contents")

    private val todoRepository = TodoRepository(TodoApplication.database.todoDao())

    fun updateTodo(id: Int, title: String, contents: String){
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateTodo(id, title, contents)
        }
    }
}