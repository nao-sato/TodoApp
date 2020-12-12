package com.example.myapplication.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.databinding.FragmentAllBinding
import com.example.myapplication.room.Todo
import kotlinx.coroutines.InternalCoroutinesApi

class AllToDoFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var ViewModel: AllToDoViewModel
    private lateinit var binding: FragmentAllBinding

    private var TodoList = ArrayList<Todo>()

    @InternalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllBinding.inflate(inflater, container, false)
        ViewModel = ViewModelProvider(this).get(AllToDoViewModel::class.java)

        val adapter = TodoListAdapter(TodoList,layoutInflater)
        val recyclerView: RecyclerView =binding.todoList
        val layout = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        )
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter

        return binding.root
    }
}