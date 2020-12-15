package com.example.myapplication

enum class TodoType(val todoTypeId: Int, val tabTextId: Int) {
    ALL(0, R.string.title_home),
    DONE(1, R.string.title_home),
    UNDONE(2, R.string.title_home);

    companion object {
        fun getTodoTypeById(id: Int): TodoType =
            values().firstOrNull { it.todoTypeId == id } ?: ALL
    }
}