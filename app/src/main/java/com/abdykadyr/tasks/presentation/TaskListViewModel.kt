package com.abdykadyr.tasks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdykadyr.tasks.data.repository.TaskRepositoryImpl
import com.abdykadyr.tasks.domain.entities.Task
import com.abdykadyr.tasks.domain.usecases.*

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepositoryImpl(application)

    private var _isTasksShown = MutableLiveData(false)
    val isTasksShown: LiveData<Boolean>
    get() = _isTasksShown

    private var _isDoneTasksShown = MutableLiveData(false)
    val isDoneTasksShown: LiveData<Boolean>
        get() = _isDoneTasksShown

    private val addTaskUseCase = AddTaskUseCase(repository)
    private val deleteTaskUseCase = DeleteTaskUseCase(repository)
    private val ediTaskUseCase = EditTaskUseCase(repository)
    private val getActiveTasksUseCase = GetActiveTasksUseCase(repository)
    private val getDoneTasksUseCase = GetDoneTasksUseCase(repository)
    private val getAllTasksUseCase = GetAllTasksUseCase(repository)
    private val getOneTaskUseCase = GetOneTaskUseCase(repository)


    fun showTasks() {
        _isTasksShown.value = true
    }

    fun hideTasks() {
        _isTasksShown.value = false
    }

    fun showDoneTasks() {
        _isDoneTasksShown.value = true
    }

    fun hideDoneTasks() {
        _isDoneTasksShown.value = false
    }

    val tasksList = getActiveTasksUseCase

    val doneTasksList = getDoneTasksUseCase

    fun getOneTask(taskId: Int) = getOneTaskUseCase(taskId)

    fun deleteTask(taskId: Int) = deleteTaskUseCase(taskId)


}