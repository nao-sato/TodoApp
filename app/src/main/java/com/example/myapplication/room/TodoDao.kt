package com.example.myapplication.room

import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun loadAllTodo(): MutableList<Todo>


    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id:Int): Todo

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(todo: Todo)

    @Query("update todo set title = :Title, contents = :Contents where id=:id")
    fun updateTodo(id: Int, Title: String, Contents: String)

    @Query("update todo set is_check = :Check where id=:id")
    fun updateChecked(id: Int, Check: Int)

    @Delete
    fun deleteTodo(todo: Todo)
}
