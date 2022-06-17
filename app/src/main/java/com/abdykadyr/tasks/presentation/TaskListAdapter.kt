package com.abdykadyr.tasks.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.transition.Visibility
import com.abdykadyr.tasks.databinding.TaskItemBinding
import com.abdykadyr.tasks.domain.entities.Task

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
            tvTaskCreatedDate.text = task.creationTime
            tvTaskCategory.text = task.category
            tvTaskTitle.text = task.description
            //checking finished time
            if (task.finishingTime != null) {
                setTaskDone(task, holder.binding)
            } else tvTaskFinishedDate.visibility = View.GONE

            if (task.countOfRepeats != Task.BASE_REPEATS_COUNT) {
                progressBar.max = task.countOfRepeats
                tvProgress.text = task.countOfRepeats.toString()
            } else {
                setTaskWithoutCounter(task, holder.binding)
            }

            if (task.plannedTime != null) {
                tvTimer.text = formatTime(task.plannedTime)
            } else {
                setTaskWithoutTimer(task, holder.binding)
            }

        }
    }

    private fun setTaskWithoutTimer(task: Task, binding: TaskItemBinding) {
        with(binding) {
            tvTimer.visibility = View.GONE
            buttonTimer.visibility = View.GONE
        }
    }

    private fun setTaskDone(task: Task, binding: TaskItemBinding) {
        with(binding) {
            tvTaskFinishedDate.text = task.finishingTime
            buttonEdit.visibility = View.GONE
            buttonIncreaseProgress.visibility = View.GONE
            buttonDecreaseProgress.visibility = View.GONE
            buttonConfirm.visibility = View.GONE
            buttonTimer.visibility = View.GONE
        }
    }

    private fun setTaskWithoutCounter(task: Task, binding: TaskItemBinding) {
        with(binding) {
            buttonIncreaseProgress.visibility = View.GONE
            buttonDecreaseProgress.visibility = View.GONE
            progressBar.visibility = View.GONE
            tvProgress.visibility = View.GONE
        }
    }

    private fun formatTime(time: Int) : String {
        return "${time / 3600}:${time / 60}:${time % 60}"
    }
}