package com.example.myapplication

import android.content.ContentValues.TAG
import android.icu.text.CaseMap
import android.util.Log
import android.widget.CheckBox
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao

//バックグラウンドでのデータのやりとりを管理を担当するクラス。
// これでUI層から通信のことを意識することはなくなる。
class TodoRepository (private val todoDao: TodoDao){
    //変更の通知を受け取るためライブデータにする。
    //このアノテーションつけると今後insert使うときにこのスレッドでやってねと怒ってくれる。
    @WorkerThread
    fun insert(todo: Todo){
        todoDao.insertTodo(todo)
    }

    @WorkerThread
    fun load():MutableList<Todo>{
        return todoDao.loadAllTodo()
    }

    @WorkerThread
    fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
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