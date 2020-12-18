package com.example.myapplication.ui.add

import androidx.lifecycle.ViewModel
import com.example.myapplication.TodoApplication.Companion.database
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTodoDialogViewModel:ViewModel() {

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