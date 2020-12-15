package com.example.myapplication.ui.all

import androidx.lifecycle.ViewModel
import com.example.myapplication.room.Todo

//データ管理担当。
//使命：リポジトリからのデータをUIに提供。それをコンフィグ変更でも維持（画面回転）
//VMはシングルトンにしない方がいいっぽい
class AllToDoViewModel: ViewModel()