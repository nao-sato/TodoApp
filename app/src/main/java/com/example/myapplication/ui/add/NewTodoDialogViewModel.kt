package com.example.myapplication.ui.add

import android.content.ContentValues.TAG
import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import com.example.myapplication.TodoApplication
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.TodoListAdapter
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTodoDialogViewModel:ViewModel() {

    private val todoRepository: TodoRepository = TodoRepository(database.todoDao())

    fun addEdiText(title:String,contents:String){
        CoroutineScope(Dispatchers.IO).launch {
            val t = Todo(title,contents,0)
            todoRepository.insert(t)
        }
    }
}
/*viewmodelを継承
* ポジティブボタンのリスな設定。
* ・ダイアログで入力されたテキストとここのデータをつなげる。
* ・ここにつなげたデータをルームに繋いで、追加処理をする（追加処理は非同期で）
* ・ここではライブデータを使う必要はない。だってあれ、UIに表示させるやつだから。*/