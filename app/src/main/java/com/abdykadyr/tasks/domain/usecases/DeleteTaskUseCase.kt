package com.abdykadyr.tasks.domain.usecases

import TasksRepository

class DeleteTaskUseCase(private val repository: TasksRepository) {
    fun invoke(taskId : Int) { repository.deleteTaskUseCase(taskId) }
}