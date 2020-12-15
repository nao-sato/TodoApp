package com.example.myapplication.ui.add

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogfragmentNewBinding

class NewTodoDialogFragment : DialogFragment() {

    private lateinit var binding: DialogfragmentNewBinding
    private val viewModel: NewTodoDialogViewModel by viewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        binding = DialogfragmentNewBinding.inflate(layoutInflater,null, false)

        builder.setView(binding.root)
            .setTitle(R.string.dia_title)
            .setPositiveButton(R.string.dia_add) { _, _ ->
                viewModel.addEdiText(binding.diaTitle.text.toString(), binding.diaContents.text.toString())
            }
            .setNegativeButton(R.string.dia_cancel) { _, _ -> }

        return builder.create()
    }
}