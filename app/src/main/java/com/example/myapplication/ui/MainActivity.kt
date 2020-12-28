package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.afollestad.materialdialogs.DialogCallback
import com.afollestad.materialdialogs.MaterialDialog
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ProgressDialogBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.add.AddTodoDialogFragment
import com.example.myapplication.ui.edit.EditTodoDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var progressDialog : MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initBinding()
        initViewModel()
        initClick()
        initBottomNavigation()
        initData()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
    }

    private fun initViewModel() {
        mainViewModel.apply {
            editTodo.observe(this@MainActivity, Observer {
                showEditDialogFragment(it)
            })
            deleteTodo.observe(this@MainActivity, Observer {
                showConfirmDeleteDialogFragment(it)
            })
            isShownProgress.observe(this@MainActivity, Observer {
                if (it)
                    showProgress()
                else
                    hideProgress()
            })
        }
    }

    private fun initClick() {
        binding.fButton.setOnClickListener{
            launchNewTodoDialogFragment()
        }
    }

    private fun initBottomNavigation() {

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_undone, R.id.navigation_all, R.id.navigation_done
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun launchNewTodoDialogFragment(){
        val dialog = AddTodoDialogFragment()
        dialog.show(supportFragmentManager,"AddDialog")
    }

    private fun initData() {
        mainViewModel.initData()
    }

    private fun showEditDialogFragment(todo: Todo) {
        EditTodoDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(EditTodoDialogFragment.KEY_ID, todo.id)
            }
        }.show(supportFragmentManager, "EditDialog")
    }

    private fun showConfirmDeleteDialogFragment(todo: Todo) {
        MaterialDialog(this).show {
            title(R.string.delete_confirm)
            negativeButton(R.string.delete_back)
            positiveButton(R.string.context_delete, click = object: DialogCallback {
                override fun invoke(p1: MaterialDialog) {
                    mainViewModel.delete(todo)
                }
            })
        }
    }

    private fun showProgress() {
        hideProgress()
        progressDialog = MaterialDialog(this).show {
            cancelable(false)
            val binding =  ProgressDialogBinding.inflate(LayoutInflater.from(context), null, false)
            setContentView(binding.root)
        }
    }

    private fun hideProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}