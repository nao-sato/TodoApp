package com.example.myapplication.ui.newtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.TodoRepository
import com.example.myapplication.room.Todo
import com.example.myapplication.room.TodoDao

class NewTodoDialogViewModel(
    application: Application,
    todoDao: TodoDao
):AndroidViewModel(application){

    private val todorepository = TodoRepository(todoDao)
    var title:String = ""
    var contents:String = ""


    suspend fun addEditext(){
        todorepository.insert(Todo(title,contents))
    }

}
/*viewmodelを継承
* ポジティブボタンのリスな設定。
* ・ダイアログで入力されたテキストとここのデータをつなげる。
* ・ここにつなげたデータをルームに繋いで、追加処理をする（追加処理は非同期で）
* ・ここではライブデータを使う必要はない。だってあれ、UIに表示させるやつだから。*/