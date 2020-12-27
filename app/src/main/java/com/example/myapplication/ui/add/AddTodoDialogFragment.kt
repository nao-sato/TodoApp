package com.example.myapplication.ui.add
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.AddTodoDialogFragmentBinding
import com.example.myapplication.ui.MainViewModel

class AddTodoDialogFragment : DialogFragment() {

    private lateinit var binding: AddTodoDialogFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        binding = AddTodoDialogFragmentBinding.inflate(layoutInflater,null, false)

        builder.setView(binding.root)
            .setTitle(R.string.dia_title)
            .setPositiveButton(R.string.dia_add){ _, _ ->
                mainViewModel.addTodo(binding.diaTitle.text.toString(),binding.diaContents.text.toString())
                    mainViewModel.initData()
            }
            .setNegativeButton(R.string.dia_cancel){ _, _ ->
            }
        return builder.create()
    }
}