package com.abdykadyr.tasks.domain.usecases

import TasksRepository
import com.abdykadyr.tasks.domain.entities.Task

class AddTaskUseCase(private val repository: TasksRepository) {
    fun invoke(task : Task) { repository.addTaskUseCase(task) }
}