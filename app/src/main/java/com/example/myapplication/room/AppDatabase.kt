package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
//DAOを使い、データベースに命令文を発行
@Database(entities = arrayOf(Todo::class),version = 1)
// entitiesというのは複数のエンティティが存在する場合のタイプを指定。
// versionはデータベースの内容に変更があった際に番号を増やして新しいバージョンである事を示す。
abstract class AppDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
// RoomDatabaseを継承した抽象クラス。
// その中の抽象メソッドはDAOのインスタンスを返す宣言がされている

    //シングルトンにすることでデータベースが同時に開かれる事を防ぐらしい
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null


        @InternalCoroutinesApi
        fun getDatabase(
                context: Context,
        ): AppDatabase {
            /*synchronizedをつけることで複数のスレッドから同時に呼ばれることなくなる。
            * 先行している処理を待ってそれが終わってからこの処理を開始する（同期実行）仕組みらしい
            * 共有データを複数スレッドから操作したら、値に矛盾が生じるからあかんらしい*/
            return INSTANCE ?: synchronized(this){
                //いでよ、データベース！
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }//とりあえずデータベースが取れる共通のメソッド完成！
}