package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Todo(title: String, contents: String, checked: Int) {
        @PrimaryKey(autoGenerate = true) var id: Int = 0
        var title: String = title
        var contents: String = contents

        @ColumnInfo(name = "is_check")
        var checked: Int = checked

        override fun toString(): String {
                return StringBuilder()
                        .append("id").append(id).append(",")
                        .append("cheke").append(checked).append(",")
                        .toString()
        }

}

