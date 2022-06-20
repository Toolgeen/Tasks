package com.abdykadyr.tasks.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TaskDbModel::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao() : TasksDao

    companion object {
        private var db : AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                db = instance
                return instance
            }

        }

    }

}