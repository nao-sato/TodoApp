package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckedTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo

//アダプタクラスは各アイテムにデータを割り当てるためのオブジェクトなので、リストデータを予めコンストラクタのフィールド内に格納しておく必要がある。
//RecyclerView.Adapterを継承する際は、ジェネリクス型パラメータとしてビューホルダークラスを指定しておく。
class TodoListAdapter(
        private val TodoList: MutableList<Todo>,
        private val layoutInflater: LayoutInflater
): RecyclerView.Adapter<TodoListAdapter.TodoHolder>() {



    override fun getItemCount() = TodoList.size

    //画面に表示される分のアイテム＋αを生成。返り値はTodoHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = RowBinding.inflate(layoutInflater,parent,false)
        //いでよ、TodoHolder！上で取得したビューを添えて。
        return TodoHolder(binding)
    }

    //引数のpositionで現在表示されているデータの位置を特定し、それに対応して第一引数のデータを取得/holderに格納をしている。
    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(TodoList[position])
    }


    //各アイテム、つまりTodo1つ1つのビューを保持するクラス
    class TodoHolder(
            //親クラスの引数に渡す引数
            private val binding: RowBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            var todoRepository = TodoRepository(TodoApplication.database.todoDao())

            binding.txtTit.text = todo.title
            binding.txtCon.text = todo.contents
            binding.check.isChecked = returnBool(todo)
            binding.check.setOnClickListener {
                var todoId = itemId.toInt()
                var falseOrTrue = jageBool(binding.check.isChecked)
                todoRepository.updateCheck(todoId, falseOrTrue)
            }
        }

        fun jageBool(isChecked: Boolean): Int {
            return if (isChecked == true) {
                1
            } else {
                0
            }
        }

        fun returnBool(todo: Todo):Boolean{
            if (todo.checked == 0){
                return false
            }else{
                return true
            }
        }
    }
}