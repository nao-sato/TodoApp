package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.myapplication.room.AppDatabase

//アプリが起動したときに初期化しておきたい処理や複数インスタンス化するような処理はここに書く
class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TodoApplication.applicationContext = applicationContext
        initialize()
    }

    private fun initialize() {
        initTimber()
        initRoom()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {

        }
    }



    private fun initRoom() {
        database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "Todo_database"
        )
                .build()
    }

    companion object {
        lateinit var applicationContext: Context
        lateinit var database: AppDatabase
    }
}