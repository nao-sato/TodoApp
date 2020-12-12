package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = DataBindingUtil.inflate<RowBinding>(
                layoutInflater,
                R.layout.row,
                parent,
                false
        )
        //いでよ、TodoHolder！上で取得したビューを添えて。
        return TodoHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(TodoList[position])
    }


    //各アイテム、つまりTodoのビューを保持するクラス
    class TodoHolder(
            //親クラスの引数に渡す引数
            private val binding: RowBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.txtTit.text = todo.title
            binding.txtCon.text = todo.contents
        }
    }

}