package com.example.myapplication.ui.todo

import android.os.Bundle
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
        var bundle = Bundle()
        bundle.putInt("id",todo.id)
        bundle.putString("title",todo.title)
        bundle.putString("contents",todo.contents)
        dialog.arguments = bundle
        dialog.show(fragmentManager,"EditDialog")
    }
}
