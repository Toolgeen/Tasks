package com.abdykadyr.tasks.domain

import androidx.lifecycle.LiveData
import com.abdykadyr.tasks.domain.entities.Task
import com.abdykadyr.tasks.domain.usecases.GetAllTasksUseCase

interface TasksRepository {

    fun addTaskUseCase(task : Task)
    fun getOneTaskUseCase(taskId : Int) : Task
    fun deleteTaskUseCase(taskId : Int)
    fun editTaskUseCase(task : Task)
    fun getActiveTasksUseCase() : LiveData<List<Task>>
    fun getAllTasksUseCase() : LiveData<List<Task>>
    fun getDoneTasksUseCase() : LiveData<List<Task>>

}