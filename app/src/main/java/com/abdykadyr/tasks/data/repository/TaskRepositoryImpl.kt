package com.abdykadyr.tasks.data.repository

import com.abdykadyr.tasks.domain.TasksRepository
import android.app.Application
import androidx.lifecycle.LiveData
import com.abdykadyr.tasks.data.database.AppDatabase
import com.abdykadyr.tasks.data.mapper.Mapper
import com.abdykadyr.tasks.domain.entities.Task

class TaskRepositoryImpl(private val application: Application) : TasksRepository {

    private val tasksDao = AppDatabase.getInstance(application).tasksDao()
    private val mapper = Mapper()

    override fun addTaskUseCase(task: Task) {
        tasksDao.addTask(mapper.mapTaskEntityToDbModel(task))
    }

    override fun getOneTaskUseCase(taskId: Int): Task {
        return mapper.mapTaskDbModelToEntity(tasksDao.getTask(taskId))
    }

    override fun deleteTaskUseCase(taskId: Int) {
        tasksDao.deleteTask(mapper.mapTaskEntityToDbModel(getOneTaskUseCase(taskId)))
    }

    override fun editTaskUseCase(task: Task) {
        tasksDao.addTask(mapper.mapTaskEntityToDbModel(task))
    }

    override fun getActiveTasksUseCase(): LiveData<List<Task>> {
        return mapper.mapTaskListDbModelToEntity(tasksDao.getActiveTasks())
    }

    override fun getAllTasksUseCase(): LiveData<List<Task>> {
        return mapper.mapTaskListDbModelToEntity(tasksDao.getAllTasks())
    }

    override fun getDoneTasksUseCase(): LiveData<List<Task>> {
        return mapper.mapTaskListDbModelToEntity(tasksDao.getDoneTasks())
    }
}