package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//DAOを使い、データベースに命令文を発行
@Database(entities = [Todo::class],version = 2)
// entitiesというのは複数のエンティティが存在する場合のタイプを指定。
// versionはデータベースの内容に変更があった際に番号を増やして新しいバージョンである事を示す。
abstract class AppDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
// RoomDatabaseを継承した抽象クラス。
// その中の抽象メソッドはDAOのインスタンスを返す宣言がされている

}
val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Todo　ADD COLUMN is_check INTEGER")
    }
}