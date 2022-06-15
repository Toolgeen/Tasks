package com.abdykadyr.tasks.domain.usecases

import com.abdykadyr.tasks.domain.TasksRepository
import com.abdykadyr.tasks.domain.entities.Task

class EditTaskUseCase(private val repository: TasksRepository) {
    operator fun invoke(task : Task) { repository.editTaskUseCase(task) }
}