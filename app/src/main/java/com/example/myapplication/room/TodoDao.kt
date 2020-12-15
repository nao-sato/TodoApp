package com.example.myapplication.room

import androidx.room.*

//データベースへの命令文をまとめたもの。
@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun loadAllTodo(): MutableList<Todo>
    // クエリーアノテーションを入れることでアウェイなクエリを理解してくれる。
    // 意味は（”todoテーブルから全てのレコードを取り出す”）となる。
    // テーブル名はエンティティ名と同名にする。（指定がない限り）
    // 1つ1つのレコードはそれに対応するエンティティインスタンスになる。
    // 複数のレコードを取り出すにはそれらをまとめた配列にする。
    // ライブデータでデータの更新を観察

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id:Int): Todo
    // SELECT文に、WHERE id ＝ :IdというようにWHERE節を追加して、指定したidレコードだけを検索するようにしている。
    /*ここではidの値を「:id」という値で指定している。この「:値」のような形の記述はプレースホルダー（値の追加場所）
    * として働く。つまり:idは、「ここにidという値をはめ込む」ということを示している*/
    //そのidの値を受け取るのがgetTodoById(id:Int)で、このメソッドの引数がそのまま＠Queryのプレースホルダーに嵌め込まれる。
    //それで検索されたデータを戻り値Todoに入れてTodoを返す。


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(todo: Todo)
    // レコードの追加を示唆するアノテーション。
    // 意味は（”追加する値が既に存在しても、無視して進む”）となる。
    // メソッドの引数に保存するエンティティインスタンスを渡すようにする。

    @Update
    fun updateTodo(todo: Todo)
    //更新を行うアノテーション
    //メソッドの引数にTodoを渡すだけ。保存のInsertと似てる。
}

//DAOインターフェースはこのように対応するアノテーションをつけてメソッドを宣言するのが基本。