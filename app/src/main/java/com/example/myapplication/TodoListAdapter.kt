package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RowBinding
import com.example.myapplication.room.Todo
import com.example.myapplication.ui.add.AddTodoDialogFragment
import com.example.myapplication.ui.edit.EditTodoDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//アダプタクラスは各アイテムにデータを割り当てるためのオブジェクトなので、リストデータを予めコンストラクタのフィールド内に格納しておく必要がある。
//RecyclerView.Adapterを継承する際は、ジェネリクス型パラメータとしてビューホルダークラスを指定しておく。
class TodoListAdapter(
        private val TodoList: MutableList<Todo>,
        private val layoutInflater: LayoutInflater
): RecyclerView.Adapter<TodoListAdapter.TodoHolder>() {


    var onClickEditTodo: ((Todo) -> Unit)? = null

    override fun getItemCount() = TodoList.size

    //画面に表示される分のアイテム＋αを生成。返り値はTodoHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val binding = RowBinding.inflate(layoutInflater,parent,false)
        //いでよ、TodoHolder！上で取得したビューを添えて。
        return TodoHolder(binding, onClickEditTodo)
    }

    //引数のpositionで現在表示されているデータの位置を特定し、それに対応して第一引数のデータを取得/holderに格納をしている。
    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(TodoList[position])
    }


    //各アイテム、つまりTodo1つ1つのビューを保持するクラス
    class TodoHolder(
            //親クラスの引数に渡す引数
            private val binding: RowBinding,
            private val onClickEditTodo: ((Todo) -> Unit)?
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            var todoRepository = TodoRepository(TodoApplication.database.todoDao())

            binding.txtTit.text = todo.title
            binding.txtCon.text = todo.contents
            binding.check.isChecked = returnBool(todo)

            binding.check.setOnClickListener {
                var falseOrTrue = jageBool(binding.check.isChecked)

                CoroutineScope(Dispatchers.IO).launch {
                    todoRepository.updateCheck(todo.id, falseOrTrue)
                }
            }

            binding.more.setOnClickListener{
                PopupMenu(itemView.context,it).also { popMenu ->
                    popMenu.menuInflater.inflate(R.menu.pop_menu, popMenu.menu)

                    popMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menuListContextDelete ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    todoRepository.deleteTodo(todo)
                                }
                            R.id.menuListContextEdit ->
                                openEditDialog(todo)
                        }
                        return@setOnMenuItemClickListener true
                    }
                } .show()
            }
        }


        private fun openEditDialog(todo: Todo){
            onClickEditTodo?.invoke(todo)
//            val dialog = EditTodoDialogFragment()
//            dialog.arguments?.putInt("id",todo.id)
//            dialog.arguments?.putString("title",todo.title)
//            dialog.arguments?.putString("contents",todo.contents)
//            dialog.show(TodoApplication.supportFragmentManager,"EditDialog")
        }

        private fun jageBool(isChecked: Boolean): Int {
            return if (isChecked == true) {
                1
            } else {
                0
            }
        }

        private fun returnBool(todo: Todo):Boolean{
           return when(todo.checked){
               1 -> true
               else -> false
           }
        }
    }
}
//この結果から分かる通り、ビューホルダーの中にリスナーを持たせることは可能で、かつ、
// それぞれのアイテムに対応した処理、表示が可能である。
// データはたくさんあるかもしれないが、ビューホルダー自体はタッチされたものに反応してくれる性質があるようだ。
// 詳しい事情はわからないが、次回に活かせそうだ。*/