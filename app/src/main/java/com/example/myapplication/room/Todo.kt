package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

//基本的には普通のクラス。ただこれはDBからデータのテーブルであるから必要なデータはここから持ってくる。
@Entity
class Todo(title: String, contents: String) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
        var title: String = title
        var contents: String = contents
}