package com.abdykadyr.tasks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.abdykadyr.tasks.data.repository.TaskRepositoryImpl
import com.abdykadyr.tasks.domain.entities.Task
import com.abdykadyr.tasks.domain.usecases.*
import java.text.SimpleDateFormat
import java.util.*

class SingleTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepositoryImpl(application)

    private val addTaskUseCase = AddTaskUseCase(repository)
    private val ediTaskUseCase = EditTaskUseCase(repository)
    private val getOneTaskUseCase = GetOneTaskUseCase(repository)

    fun getOneTask(taskId: Int) = getOneTaskUseCase(taskId)

    fun addTask(
        inputDescription: String?,
        category: String,
        finishingDay: String? = null,
        finishingTime: String? = null,
        countOfRepeats: Int = Task.BASE_REPEATS_COUNT,
        plannedTime: Int? = null
    ) {
        val description = parseDescription(inputDescription)
        val creationTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            .format(Calendar.getInstance().time)
        val deadline = validateDeadline(finishingDay, finishingTime)
    }

    private fun validateDeadline(finishingDay: String?, finishingTime: String?): String {
        return if (finishingDay != null) {
            finishingDay + finishingTime
        } else ""
    }

    private fun parseDescription(inputDescription: String?): String {
        return inputDescription?.trim() ?: ""
    }



}