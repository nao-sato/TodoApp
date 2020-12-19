package com.example.myapplication.ui.edit

import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoDialogViewModel : ViewModel() {

    val ETDF = EditTodoDialogFragment()
    var id = ETDF.arguments?.getInt("id")
    var title = ETDF.arguments?.getString("title")
    var contents = ETDF.arguments?.getString("contents")



    private val todoRepository = TodoRepository(TodoApplication.database.todoDao())
    fun updateTodo(id: Int, title: String, contents: String){
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateTodo(id, title, contents)
        }
    }
}