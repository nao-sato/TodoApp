package com.example.todoapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.todoapp.ToDo
import java.lang.reflect.Field

class MainViewModel : ViewModel() {
    var data: MutableList<ToDo> = mutableListOf(
            ToDo("タイトル", "内容")
    )

    get() = Field


}