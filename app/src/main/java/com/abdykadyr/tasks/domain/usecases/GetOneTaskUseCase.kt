package com.abdykadyr.tasks.domain.usecases

import TasksRepository
import com.abdykadyr.tasks.domain.entities.Task

class GetOneTaskUseCase(private val repository: TasksRepository) {
    fun invoke(taskId: Int): Task { return repository.getOneTaskUseCase(taskId) }
}