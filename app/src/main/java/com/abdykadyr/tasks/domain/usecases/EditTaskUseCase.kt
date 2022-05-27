package com.abdykadyr.tasks.domain.usecases

import TasksRepository
import com.abdykadyr.tasks.domain.entities.Task

class EditTaskUseCase(private val repository: TasksRepository) {
    fun invoke(task : Task) { repository.editTaskUseCase(task) }
}