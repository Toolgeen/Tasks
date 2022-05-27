package com.abdykadyr.tasks.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks_table")
data class TaskDbModel(

    //base tasks
    val name: String,
    val description: String,
    val category: String,
    val creationTime: Int,
    val deadline: DeadlineDbModel,
    val finishingTime: Int? = null,
    //recurred tasks
    val countOfRepeats: Int,
    val countOfRepeatsDone: Int,
    //timed tasks
    val plannedTime: Int? = null,
    val spentTime: Int? = null,
    @PrimaryKey
    val id: Int

)