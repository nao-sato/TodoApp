package com.example.myapplication.ui.edit

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.AddTodoDialogFragmentBinding
import com.example.myapplication.databinding.EditTodoDialogFragmentBinding
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.add.AddTodoDialogViewModel

class EditTodoDialogFragment : DialogFragment() {
    private lateinit var binding: EditTodoDialogFragmentBinding
    private val viewModel: EditTodoDialogViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        binding = EditTodoDialogFragmentBinding.inflate(layoutInflater,null, false)

        builder.setView(binding.root)
                .setTitle(R.string.context_edit)
                .setPositiveButton(R.string.dia_add) { _, _ ->
                    viewModel.updateTodo(binding.diaTitle.text.toString(), binding.diaContents.text.toString())
                        mainViewModel.initData()
                }

                .setNegativeButton(R.string.dia_cancel){ _, _ ->
                }

        Handler(Looper.getMainLooper()).postDelayed({
            initViewModel()
        }, 10L)
        return builder.create()
    }

    private fun initViewModel() {
        viewModel.apply {
            title.observe(this@EditTodoDialogFragment, Observer {
                binding.title = it
            })
            contents.observe(this@EditTodoDialogFragment, Observer {
                binding.contents = it
            })
            initData(arguments)
        }
    }
}