package com.example.myapplication.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.databinding.FragmentUndoneBinding

class UndoneToDoFragment : BaseTodoFragment() {

    lateinit var binding:FragmentUndoneBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUndoneBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        layout()
        mainViewModel.loadUndoneTodo()
    }

    private fun layout(){
        initRecyclerView(binding.todoList,  mainViewModel.layout, mainViewModel.adapter)
    }
}