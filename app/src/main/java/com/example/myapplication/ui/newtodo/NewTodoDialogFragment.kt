package com.example.myapplication.ui.newtodo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogfragmentNewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class NewTodoDialogFragment(): DialogFragment() {

    private lateinit var binding: DialogfragmentNewBinding
    private val viewModel: NewTodoDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        binding = DialogfragmentNewBinding.inflate(layoutInflater, null, false)
//        binding = DataBindingUtil.inflate<DialogfragmentNewBinding>(inflater,R.layout.dialogfragment_new,null,false)
//        binding.viewmodel = viewModel

        builder.setView(binding.root)
            .setTitle(R.string.dia_title)
            .setPositiveButton(R.string.dia_add){dialog, id ->
                viewModel.addEdiText()
            }
            .setNegativeButton(R.string.dia_cancel){dialog, id ->
            }
        Handler(Looper.getMainLooper()).postDelayed({
            binding.viewmodel = viewModel
        }, 500L)
        return builder.create()
    }
}