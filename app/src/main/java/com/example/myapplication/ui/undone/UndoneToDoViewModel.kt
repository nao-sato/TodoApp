package com.example.myapplication.ui.undone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UndoneToDoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "未完了リスト"
    }
    val text: LiveData<String> = _text
}