package com.example.myapplication.ui.todo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDoneBinding
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.databinding.FragmentUndoneBinding

class UndoneToDoFragment : Fragment() {

    private lateinit var binding: FragmentUndoneBinding

    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentUndoneBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        initLayout()
        Handler(Looper.getMainLooper()).postDelayed({
            initViewModel()
        }, 200L)
    }

    private fun initLayout() {
        initRecyclerView()
        initSwipeRefreshLayout()
    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.updateData()
        }
    }

    private fun initRecyclerView() {
        binding.articlesView.customAdapter.emptyText = getString(R.string.empty_register)
    }

    private fun initViewModel() {
        mainViewModel.apply {
            items.observe(viewLifecycleOwner, Observer { list ->
                binding.articlesView.customAdapter.refresh(list.filter { it.checked == 0 })
                binding.swipeRefreshLayout.isRefreshing = false
            })
        }
    }
}