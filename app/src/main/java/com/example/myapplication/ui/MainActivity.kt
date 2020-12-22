package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoPagerAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.add.AddTodoDialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        binding.viewPager.apply {
            adapter = TodoPagerAdapter(supportFragmentManager)
            addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageSelected(position: Int) {
                    when(position) {
                        0 -> binding.navView.menu.findItem(R.id.navigation_all).isChecked = true
                        1 -> binding.navView.menu.findItem(R.id.navigation_undone).isChecked = true
                        else -> binding.navView.menu.findItem(R.id.navigation_done).isChecked = true
                    }
                }
            })
        }

        binding.navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            binding.viewPager.currentItem = when(item.itemId) {
                R.id.navigation_all -> 0
                R.id.navigation_undone -> 1
                else -> 2
            }
            return@OnNavigationItemSelectedListener true
        })

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_undone, R.id.navigation_all, R.id.navigation_done
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.fButton.setOnClickListener{
            launchNewTodoDialogFragment()
        }
    }
    private fun launchNewTodoDialogFragment(){
        val dialog = AddTodoDialogFragment()
        dialog.show(supportFragmentManager,"AddDialog")
    }
}