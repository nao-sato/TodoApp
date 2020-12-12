package com.example.myapplication.ui.undone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class UndoneToDoFragment : Fragment() {

    private lateinit var undoneToDoViewModel: UndoneToDoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        undoneToDoViewModel =
                ViewModelProvider(this).get(UndoneToDoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_undone, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        undoneToDoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}