package com.abdykadyr.tasks.domain.usecases

import com.abdykadyr.tasks.domain.TasksRepository
import com.abdykadyr.tasks.domain.entities.Task

class GetOneTaskUseCase(private val repository: TasksRepository) {
    operator fun invoke(taskId: Int): Task { return repository.getOneTaskUseCase(taskId) }
}