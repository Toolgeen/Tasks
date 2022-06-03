package com.abdykadyr.tasks.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskDbModel(

    //base tasks
    val description: String,
    val category: String,
    val creationTime: Int,
    val finishingTime: Int? = null,
    //recurred tasks
    val countOfRepeats: Int,
    var countOfRepeatsDone: Int,
    //timed tasks
    val plannedTime: Int? = null,
    var spentTime: Int? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int

)