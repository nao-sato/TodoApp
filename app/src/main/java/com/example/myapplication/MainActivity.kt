package com.example.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.add.NewTodoDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_undone, R.id.navigation_all, R.id.navigation_done))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.fButton.setOnClickListener{
            launchNewTodoDialogFragment()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val list = database.todoDao().loadAllTodo()
            Timber.d("list.size:${list.size} list:$list")
        }
        viewModel.updateData()
    }

    private fun test(type: TodoType) {
        when(type) {
            TodoType.ALL -> {}
            TodoType.DONE -> {}
            else -> {}
        }

        val id = type.todoTypeId

        Fragment().apply {
            arguments = Bundle().apply {
                putInt("todo_type_id", type.todoTypeId)
            }
        }
    }

    private fun launchNewTodoDialogFragment(){
        val dialog = NewTodoDialogFragment()
        dialog.show(supportFragmentManager,"Dialog")
    }
}