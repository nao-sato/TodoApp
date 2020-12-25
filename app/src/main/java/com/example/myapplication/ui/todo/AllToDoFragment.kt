package com.example.myapplication.ui.todo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentAllBinding
import com.example.myapplication.ui.MainViewModel

class AllToDoFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        Handler(Looper.getMainLooper()).postDelayed({
            initViewModel()
        }, 200L)
    }

    private fun initViewModel() {
        mainViewModel.apply {
            items.observe(viewLifecycleOwner, Observer {
                binding.todosView.customAdapter.refresh(it)
                Log.d(TAG,"refresh:$it")
            })
        }
    }
}