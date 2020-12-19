package com.example.myapplication.ui.todo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.TodoApplication
import com.example.myapplication.databinding.FragmentAllBinding

class AllToDoFragment : BaseTodoFragment() {

    private lateinit var binding: FragmentAllBinding

    private val menuInflater = MenuInflater(TodoApplication.applicationContext)
    private val mainViewModel: MainViewModel by viewModels()

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
        mainViewModel.loadAllTodo()
    }

    private fun layout(){
        initRecyclerView(binding.todoList,  mainViewModel.layout, mainViewModel.adapter)
    }
    /*
    private fun listener(){
        val binding = RowBinding.inflate(LayoutInflater.from(TodoApplication.applicationContext))
        binding.check.setOnClickListener {
            shareViewModel.isChecked(binding.check)
        }
    }*/


}