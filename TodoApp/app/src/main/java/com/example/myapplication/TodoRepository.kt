package com.example.myapplication

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao

//バックグラウンドでのデータのやりとりを管理を担当するクラス。
// これでUI層から通信のことを意識することはなくなる。
class TodoRepository (private val todoDao: TodoDao){
    //変更の通知を受け取るためライブデータにする。
    var Alltodo: LiveData<List<Todo>> = todoDao.loadAllTodo()

    //このアノテーションつけると今後insert使うときにこのスレッドでやってねと怒ってくれる。
    @WorkerThread
    suspend fun insert(todo: Todo){
        todoDao.insertTodo(todo)
    }

}