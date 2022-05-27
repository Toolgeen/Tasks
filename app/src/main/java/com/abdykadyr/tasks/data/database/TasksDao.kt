package com.abdykadyr.tasks.data.database

import androidx.room.*

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table WHERE countOfRepeats == countOfRepeatsDone " +
            "ORDER BY creationTime DESC")
    fun getActiveTasks()

    @Query("SELECT * FROM tasks_table WHERE countOfRepeats != countOfRepeatsDone " +
            "ORDER BY creationTime DESC")
    fun getDoneTasks()

    @Query("SELECT * FROM tasks_table ORDER BY creationTime DESC")
    fun getAllTasks()

    @Delete(entity = TaskDbModel::class)
    fun deleteTask(taskId: Int)
    //TODO test this

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(taskDbModel: TaskDbModel)



}