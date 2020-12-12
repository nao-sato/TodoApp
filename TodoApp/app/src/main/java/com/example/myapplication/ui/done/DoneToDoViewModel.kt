package com.example.myapplication.ui.done

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DoneToDoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "完了リスト"
    }
    val text: LiveData<String> = _text
}