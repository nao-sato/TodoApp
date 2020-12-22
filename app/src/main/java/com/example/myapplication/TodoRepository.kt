package com.example.myapplication

import android.content.ContentValues.TAG
import android.icu.text.CaseMap
import android.util.Log
import android.widget.CheckBox
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao

class TodoRepository (private val todoDao: TodoDao){
    @WorkerThread
    fun insert(todo: Todo){
        todoDao.insertTodo(todo)
    }

    @WorkerThread
    fun load():MutableList<Todo>{
        return todoDao.loadAllTodo()
    }

    @WorkerThread
    fun updateTodo(id:Int,title:String,contents:String){
        todoDao.updateTodo(id,title,contents)
    }

    @WorkerThread
    fun updateCheck(id:Int,checked:Int){
        todoDao.updateChecked(id, checked)
    }

    @WorkerThread
    fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }

}