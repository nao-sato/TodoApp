package com.example.myapplication.ui.edit

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.AddTodoDialogFragmentBinding
import com.example.myapplication.databinding.EditTodoDialogFragmentBinding
import com.example.myapplication.ui.MainViewModel
import com.example.myapplication.ui.add.AddTodoDialogViewModel

class EditTodoDialogFragment : DialogFragment() {
    private lateinit var binding: EditTodoDialogFragmentBinding
    private val viewModel: EditTodoDialogViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity)
        binding = EditTodoDialogFragmentBinding.inflate(layoutInflater,null, false)




        builder.setView(binding.root)
                .setTitle(R.string.dia_title)
                .setPositiveButton(R.string.dia_add){ _, _ ->
                    viewModel.updateTodo(viewModel.id!!,viewModel.title!!,viewModel.contents!!)
                    mainViewModel.loadAllTodo()
                }
                .setNegativeButton(R.string.dia_cancel){ _, _ ->
                }

        return builder.create()
    }

}