package com.example.myapplication.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentDoneBinding
import com.example.myapplication.ui.MainViewModel

class DoneToDoFragment : BaseTodoFragment() {

    lateinit var binding:FragmentDoneBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

       /* mainViewModel.observeData.observe(viewLifecycleOwner, Observer {
            mainViewModel.loadDoneTodo()
        })*/
    }

    private fun initialize(){
        initRecyclerView(binding.todoList,  mainViewModel.layout, mainViewModel.adapter)
        mainViewModel.loadDoneTodo()
    }
}