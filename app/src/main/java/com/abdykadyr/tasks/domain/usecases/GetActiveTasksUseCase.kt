package com.abdykadyr.tasks.domain.usecases

import com.abdykadyr.tasks.domain.TasksRepository
import androidx.lifecycle.LiveData
import com.abdykadyr.tasks.domain.entities.Task

class GetActiveTasksUseCase(private val repository: TasksRepository) {
    operator fun invoke(): LiveData<List<Task>> { return repository.getActiveTasksUseCase() }

}