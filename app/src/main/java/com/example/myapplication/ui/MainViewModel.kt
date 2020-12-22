package com.example.myapplication.ui

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

    fun updateTodoCheck(todo: Todo) {
        val isCurrentCheck = todo.checked == 1
        val willChecked = !isCurrentCheck
        CoroutineScope(Dispatchers.IO).launch {
            todoRepository.updateCheck(todo.id, if (willChecked) 1 else 0)
            updateData()
        }
    }

    fun initData() {
        updateData()
    }

    fun willDelete(todo: Todo) {
        deleteTodo.postValue(todo)
    }

    fun delete(todo: Todo) {
        //TODO:deleteする何か
    }

    fun edit(todo: Todo) {
        editTodo.postValue(todo)
    }

    private fun updateData() {
        CoroutineScope(Dispatchers.IO).launch {
            items.postValue(todoRepository.load())
        }
    }

//    private var todoList = mutableListOf<Todo>()
//    private var todoRepository: TodoRepository = TodoRepository(TodoApplication.database.todoDao())
//    var adapter = TodoListAdapter(LayoutInflater.from(TodoApplication.applicationContext))
//    var layout = LinearLayoutManager(TodoApplication.applicationContext)
//    //val observeData  = MutableLiveData<MutableList<Todo>>()
//
//
//
//
//    fun loadAllTodo() {
//        CoroutineScope(Dispatchers.IO).launch {
//            todoList = todoRepository.load()
//          //  observeData.postValue(todoList)
//            withContext(Dispatchers.Main){
//                adapter.refresh(todoList)
//            }
//        }
//    }
//
//    fun loadDoneTodo(){
//        CoroutineScope(Dispatchers.IO).launch {
//            todoList = todoRepository.load()
//            withContext(Dispatchers.Main) {
//                val doneList: MutableList<Todo> = todoList.filter { it.checked == 1 } as MutableList<Todo>
//                adapter.refresh(doneList)
//               // observeData.postValue(doneList)
//            }
//        }
//    }
//
//    fun loadUndoneTodo(){
//        CoroutineScope(Dispatchers.IO).launch {
//            todoList = todoRepository.load()
//            withContext(Dispatchers.Main) {
//                val undoneList: MutableList<Todo> = todoList.filter { it.checked == 0 } as MutableList<Todo>
//                adapter.refresh(undoneList)
//               // observeData.postValue(undoneList)
//            }
//        }
//    }
}