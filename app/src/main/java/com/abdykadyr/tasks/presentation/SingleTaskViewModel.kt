package com.abdykadyr.tasks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _requiredDeadline = MutableLiveData<Boolean>(false)
    val requiredDeadline: LiveData<Boolean>
    get() = _requiredDeadline

    private var _requiredTimer = MutableLiveData<Boolean>(false)
    val requiredTimer: LiveData<Boolean>
        get() = _requiredTimer

    private var _requiredRepeatsCounter = MutableLiveData<Boolean>(false)
    val requiredRepeatsCounter: LiveData<Boolean>
        get() = _requiredRepeatsCounter

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
        addTaskUseCase.invoke(Task(
                description,
                category,
                creationTime,
                plannedTime = plannedTime,
                countOfRepeats = countOfRepeats
            )
        )
    }

    fun turnOnDeadline() {
        _requiredDeadline.value = true
    }

    fun turnOffDeadline() {
        _requiredDeadline.value = false
    }

    fun turnOnTimer() {
        _requiredTimer.value = true
    }

    fun turnOffTimer() {
        _requiredTimer.value = false
    }

    fun turnOnRepeatsCounter() {
        _requiredRepeatsCounter.value = true
    }

    fun turnOffRepeatsCounter() {
        _requiredRepeatsCounter.value = false
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