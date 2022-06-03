package com.abdykadyr.tasks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.abdykadyr.tasks.data.repository.TaskRepositoryImpl

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepositoryImpl(application)



}