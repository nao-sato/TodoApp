package com.example.myapplication.ui.newtodo

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogfragmentNewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoDialogFragment(
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val viewModel = ViewModelProvider(this).get(NewTodoDialogViewModel::class.java)
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val binding = DataBindingUtil.inflate<DialogfragmentNewBinding>(inflater,R.layout.dialogfragment_new,null,false)
        binding.viewmodel = viewModel

        builder.setView(binding.root)
            .setTitle(R.string.dia_title)
            .setPositiveButton(R.string.dia_add){dialog, id ->
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.addEditext()
                }
            }
            .setNegativeButton(R.string.dia_cancel){dialog, id ->
            }
        return builder.create()
    }
}