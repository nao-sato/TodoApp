package com.example.myapplication.ui.all

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.*
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.Todo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//データ管理担当。
//使命：リポジトリからのデータをUIに提供。それをコンフィグ変更でも維持（画面回転）
//VMはシングルトンにしない方がいいっぽい
@InternalCoroutinesApi
class AllToDoViewModel(application: Application):AndroidViewModel(application) {


}