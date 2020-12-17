package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ActivityViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.databinding.FragmentAllBinding
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class AllToDoFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private val shareViewModel: ActivityViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        layout()
     //   listener()
    }

    private fun layout(){
        val recyclerView: RecyclerView = binding.todoList
        recyclerView.layoutManager = shareViewModel.layout
        recyclerView.adapter = shareViewModel.adapter

        shareViewModel.loadTodo()
    }

    /*
    private fun listener(){
        val binding = RowBinding.inflate(LayoutInflater.from(TodoApplication.applicationContext))
        binding.check.setOnClickListener {
            shareViewModel.isChecked(binding.check)
        }
    }*/


}