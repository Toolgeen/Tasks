package com.abdykadyr.tasks.domain.usecases

import com.abdykadyr.tasks.domain.TasksRepository

class DeleteTaskUseCase(private val repository: TasksRepository) {
    operator fun invoke(taskId : Int) { repository.deleteTaskUseCase(taskId) }
}