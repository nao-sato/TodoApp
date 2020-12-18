package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.ui.todo.AllToDoFragment
import com.example.myapplication.ui.todo.DoneToDoFragment
import com.example.myapplication.ui.todo.UndoneToDoFragment

class TodoPagerAdapter(fragmentManager: FragmentManager):FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> AllToDoFragment()
            1 -> UndoneToDoFragment()
            else -> DoneToDoFragment()
        }
    }
}