package com.abdykadyr.tasks.data.database

import androidx.room.TypeConverter
import java.lang.RuntimeException

class Converters {

    @TypeConverter
    fun DeadlineToInt(deadlineDbModel: DeadlineDbModel) : Int {
        return when (deadlineDbModel) {
            DeadlineDbModel.DAY -> DAY_INDEX
            DeadlineDbModel.WEEK -> WEEK_INDEX
            DeadlineDbModel.MONTH -> MONTH_INDEX
            DeadlineDbModel.YEAR -> YEAR_INDEX
        }
    }

    @TypeConverter
    fun IntToDeadline(index : Int) : DeadlineDbModel {
        return when (index) {
            DAY_INDEX -> DeadlineDbModel.DAY
            WEEK_INDEX -> DeadlineDbModel.WEEK
            MONTH_INDEX -> DeadlineDbModel.MONTH
            YEAR_INDEX -> DeadlineDbModel.YEAR
            else -> throw RuntimeException("Unknown index of deadline $index")
        }
    }


    companion object {
        private const val DAY_INDEX = 1
        private const val WEEK_INDEX = 2
        private const val MONTH_INDEX = 3
        private const val YEAR_INDEX = 4
    }
}