package com.example.myapplication.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.databinding.FragmentAllBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class AllToDoFragment : Fragment() {

    private lateinit var viewModel: AllToDoViewModel
    private lateinit var binding: FragmentAllBinding

    private val todoRepository:TodoRepository = TodoRepository(database.todoDao())

    private var todoList = ArrayList<Todo>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AllToDoViewModel::class.java)

        val adapter = TodoListAdapter(todoList,layoutInflater)
        val recyclerView: RecyclerView =binding.todoList
        val layout = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        )
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
           binding.test.text = getByText(todoRepository.load())
        }


        return binding.root
    }
    fun getByText(data:MutableList<Todo>):String{
        var res = ""
        for (item in data) {
            res += item.to_s()
            res += "\n"
        }
        return res
    }
}