package com.example.myapplication.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.TodoApplication.Companion.database
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

    private val mainViewModel: MainViewModel by activityViewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initViewModel()
        initLayout()
        initData()
    }

    private fun initViewModel() {
        val todoType = TodoType.getTodoTypeById(arguments?.getInt("key_todo_type_id") ?: 0)
        mainViewModel.items.observe(viewLifecycleOwner, Observer { todos ->
            val list = when(todoType) {
                TodoType.DONE -> todos.filterNot { it.completedAt == 0L }
                TodoType.UNDONE -> todos.filter { it.completedAt == 0L }
                else -> todos
            }
            // tasksView.customAdapter.refresh(list)
            binding.swipeRefreshLayout.isRefresh = false
        })
    }

    private fun initLayout() {
        initClick()
        initRecyclerView()
        initSwipeRefreshLayout()
    }

    private fun initClick() {

    }

    private fun initRecyclerView() {

    }

    private fun initSwipeRefreshLayout() {
        binding.swipeRefreshLayout.onRefresh {
            mainViewModel.updateData()
        }
    }

    private fun initData() {
        viewModel.initData()
    }

    fun getByText(data:List<Todo>):String{
        var res = ""
        for (item in data) {
            res += item.to_s()
            res += "\n"
        }
        return res
    }
}