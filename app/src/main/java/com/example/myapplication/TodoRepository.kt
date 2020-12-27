package com.example.myapplication

import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao

class TodoRepository (private val todoDao: TodoDao){
    suspend fun insert(todo: Todo){
        todoDao.insertTodo(todo)
    }

    suspend fun load():MutableList<Todo>{
        return todoDao.loadAllTodo()
    }

    suspend fun loadById(id: Int): Todo? {
        return todoDao.getTodoById(id)
    }

    suspend fun updateTodo(id:Int,title:String,contents:String){
        todoDao.updateTodo(id,title,contents)
    }

    suspend fun updateCheck(id:Int,checked:Int){
        todoDao.updateChecked(id, checked)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }

}