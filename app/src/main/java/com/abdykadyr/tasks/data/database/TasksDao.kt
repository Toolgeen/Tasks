package com.abdykadyr.tasks.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table WHERE  id == :taskId ")
    fun getTask(taskId: Int) : TaskDbModel

    @Query("SELECT * FROM tasks_table WHERE countOfRepeats == countOfRepeatsDone " +
            "ORDER BY creationTime DESC")
    fun getActiveTasks() : LiveData<List<TaskDbModel>>

    @Query("SELECT * FROM tasks_table WHERE countOfRepeats != countOfRepeatsDone " +
            "ORDER BY creationTime DESC")
    fun getDoneTasks() : LiveData<List<TaskDbModel>>

    @Query("SELECT * FROM tasks_table ORDER BY creationTime DESC")
    fun getAllTasks() : LiveData<List<TaskDbModel>>

    @Delete(entity = TaskDbModel::class)
    fun deleteTask(taskDbModel: TaskDbModel)
    //TODO test this

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(taskDbModel: TaskDbModel)



}