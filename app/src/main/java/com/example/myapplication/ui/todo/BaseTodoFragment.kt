package com.example.myapplication.ui.todo

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.edit.EditTodoDialogFragment

open class BaseTodoFragment: Fragment() {


    protected fun initRecyclerView(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager, adapter: TodoListAdapter) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.onClickEditTodo = {
            showEditDialogFragment(it)
        }
    }

    private fun showEditDialogFragment(todo: Todo) {
        val fragmentManager = activity?.supportFragmentManager ?: return
        val dialog = EditTodoDialogFragment()
        dialog.arguments?.putInt("id",todo.id)
        dialog.arguments?.putString("title",todo.title)
        dialog.arguments?.putString("contents",todo.contents)
        dialog.show(fragmentManager,"EditDialog")
    }
}