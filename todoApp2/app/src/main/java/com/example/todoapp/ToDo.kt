package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDo(title: String, contents: String) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var title: String = title
    var contents: String = contents
}