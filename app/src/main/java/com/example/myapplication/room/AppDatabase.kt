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
// その中の抽象メソッドはDAOのインスタンスを返す宣言がされてい
}
