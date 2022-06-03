package com.abdykadyr.tasks.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.abdykadyr.tasks.databinding.TaskItemBinding
import com.abdykadyr.tasks.domain.entities.Task
import java.time.LocalDateTime

class TaskListAdapter: ListAdapter<Task,TaskItemViewHolder>(TaskDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
        return TaskItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = getItem(position)
        with(holder.binding) {

        }
    }
}