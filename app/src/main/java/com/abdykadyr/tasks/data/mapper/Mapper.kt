package com.abdykadyr.tasks.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.abdykadyr.tasks.data.database.DeadlineDbModel
import com.abdykadyr.tasks.data.database.TaskDbModel
import com.abdykadyr.tasks.domain.entities.Deadline
import com.abdykadyr.tasks.domain.entities.Task

class Mapper {

    fun mapTaskEntityToDbModel(task: Task) : TaskDbModel {
        return TaskDbModel(
            name = task.name,
            description = task.description,
            category = task.category,
            creationTime = task.creationTime,
            deadline = mapDeadlineEntityToModel(task.deadline),
            finishingTime = task.finishingTime,
            countOfRepeats = task.countOfRepeats,
            countOfRepeatsDone = task.countOfRepeatsDone,
            plannedTime = task.plannedTime,
            spentTime = task.spentTime,
            id = task.id
        )
    }

    private fun mapDeadlineEntityToModel(deadline: Deadline) : DeadlineDbModel {
        return when (deadline) {
            Deadline.DAY -> DeadlineDbModel.DAY
            Deadline.WEEK -> DeadlineDbModel.WEEK
            Deadline.MONTH -> DeadlineDbModel.MONTH
            Deadline.YEAR -> DeadlineDbModel.YEAR
        }
    }

    fun mapTaskDbModelToEntity(taskModel: TaskDbModel) : Task {
        return Task(
            name = taskModel.name,
            description = taskModel.description,
            category = taskModel.category,
            creationTime = taskModel.creationTime,
            deadline = mapDeadlineModelToEntity(taskModel.deadline),
            finishingTime = taskModel.finishingTime,
            countOfRepeats = taskModel.countOfRepeats,
            countOfRepeatsDone = taskModel.countOfRepeatsDone,
            plannedTime = taskModel.plannedTime,
            spentTime = taskModel.spentTime,
            id = taskModel.id
        )
    }

    private fun mapDeadlineModelToEntity(deadlineDbModel: DeadlineDbModel) : Deadline {
        return when (deadlineDbModel) {
            DeadlineDbModel.DAY -> Deadline.DAY
            DeadlineDbModel.WEEK -> Deadline.WEEK
            DeadlineDbModel.MONTH -> Deadline.MONTH
            DeadlineDbModel.YEAR -> Deadline.YEAR
        }
    }

    fun mapTaskListDbModelToEntity(list: LiveData<List<TaskDbModel>>) : LiveData<List<Task>> {
        return Transformations.map(list) {
            it.map { taskDbModel ->
                mapTaskDbModelToEntity(taskDbModel)
            }
        }
    }
}