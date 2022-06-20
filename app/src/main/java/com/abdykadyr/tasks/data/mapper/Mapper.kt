package com.abdykadyr.tasks.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.abdykadyr.tasks.data.database.TaskDbModel
import com.abdykadyr.tasks.domain.entities.Task

class Mapper {

    fun mapTaskEntityToDbModel(task: Task) : TaskDbModel {
        return TaskDbModel(
            description = task.description,
            category = task.category,
            creationTime = task.creationTime,
            finishingTime = task.finishingTime,
            countOfRepeats = task.countOfRepeats,
            countOfRepeatsDone = task.countOfRepeatsDone,
            plannedTime = task.plannedTime,
            spentTime = task.spentTime,
            id = task.id
        )
    }


    fun mapTaskDbModelToEntity(taskModel: TaskDbModel) : Task {
        return Task(
            description = taskModel.description,
            category = taskModel.category,
            creationTime = taskModel.creationTime,
            finishingTime = taskModel.finishingTime,
            countOfRepeats = taskModel.countOfRepeats,
            countOfRepeatsDone = taskModel.countOfRepeatsDone,
            plannedTime = taskModel.plannedTime,
            spentTime = taskModel.spentTime,
            id = taskModel.id
        )
    }

    fun mapTaskListDbModelToEntity(list: LiveData<List<TaskDbModel>>) : LiveData<List<Task>> {
        return Transformations.map(list) {
            it.map { taskDbModel ->
                mapTaskDbModelToEntity(taskDbModel)
            }
        }
    }
}