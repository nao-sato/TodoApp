package com.example.myapplication.ui.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class DoneToDoFragment : Fragment() {

    private lateinit var doneToDoViewModel: DoneToDoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        doneToDoViewModel =
                ViewModelProvider(this).get(DoneToDoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_done, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        doneToDoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}