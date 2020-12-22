package com.example.myapplication.ui

import android.os.Bundle
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
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.add.AddTodoDialogFragment
import com.example.myapplication.ui.edit.EditTodoDialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        initBinding()
        initViewModel()
        initClick()
        initBottomNavigation()
        initViewPager2()
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
        }
    }

    private fun initClick() {
        binding.fButton.setOnClickListener{
            launchNewTodoDialogFragment()
        }
    }

    private fun initBottomNavigation() {
        binding.navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            binding.viewPager.currentItem = when(item.itemId) {
//                R.id.navigation_all -> 0
//                R.id.navigation_undone -> 1
//                else -> 2
//            }
            return@OnNavigationItemSelectedListener true
        })

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_undone, R.id.navigation_all, R.id.navigation_done
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun initViewPager2() {
//        binding.viewPager.apply {
//            adapter = TodoPagerAdapter(supportFragmentManager)
//            addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
//                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//                override fun onPageScrollStateChanged(state: Int) {}
//                override fun onPageSelected(position: Int) {
//                    when(position) {
//                        0 -> binding.navView.menu.findItem(R.id.navigation_all).isChecked = true
//                        1 -> binding.navView.menu.findItem(R.id.navigation_undone).isChecked = true
//                        else -> binding.navView.menu.findItem(R.id.navigation_done).isChecked = true
//                    }
//                }
//            })
//        }
    }

    private fun launchNewTodoDialogFragment(){
        val dialog = AddTodoDialogFragment()
        dialog.show(supportFragmentManager,"AddDialog")
    }

    private fun initData() {
        mainViewModel.initData()
    }

    private fun showEditDialogFragment(todo: Todo) {
        val dialog = EditTodoDialogFragment()
        var bundle = Bundle()
        bundle.putInt("id", todo.id)
        bundle.putString("title", todo.title)
        bundle.putString("contents", todo.contents)
        dialog.arguments = bundle
        dialog.show(supportFragmentManager, "EditDialog")
    }

    private fun showConfirmDeleteDialogFragment(todo: Todo) {
        MaterialDialog(this).show {
            title(text = "消しますか？")
            negativeButton(text = "キャンセル")
            positiveButton(text = "消す", click = object: DialogCallback {
                override fun invoke(p1: MaterialDialog) {
                    mainViewModel.delete(todo)
                }
            })
        }
    }
}