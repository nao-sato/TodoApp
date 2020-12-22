package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Todo::class],version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}
