package com.abdykadyr.tasks.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abdykadyr.tasks.R
import com.abdykadyr.tasks.databinding.FragmentTaskListBinding

class TaskListFragment: Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding: FragmentTaskListBinding
    get() = _binding ?: throw RuntimeException("FragmentTaskListBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[TaskListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskListAdapter = taskListAdapter()
        viewModelObservers(taskListAdapter)

    }

    private fun viewModelObservers(taskListAdapter: TaskListAdapter) {
        viewModel.isTasksShown.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.tasksList.invoke().observe(viewLifecycleOwner) { it1 ->
                    taskListAdapter.submitList(it1)
                }
            } else {
                taskListAdapter.submitList(null)
            }
        }

        viewModel.isDoneTasksShown.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.doneTasksList.invoke().observe(viewLifecycleOwner) { it1 ->
                    taskListAdapter.submitList(it1)
                }
            } else {
                taskListAdapter.submitList(null)
            }
        }
    }

    private fun taskListAdapter(): TaskListAdapter {
        val taskListAdapter = TaskListAdapter()
        binding.rvTaskList.adapter = taskListAdapter
        settingUpSwitches()
        taskListAdapter.onDeleteButtonClick = {
            viewModel.deleteTask(it)
        }
        taskListAdapter.onEditButtonClick = {
            launchEditFragment(it)
        }
        taskListAdapter.onConfirmButtonClick = {
            viewModel.increaseCounterInTask(it)
        }
        taskListAdapter.onDecreaseProgressButtonClick = {
            viewModel.decreaseCounterInTask(it)
        }
        taskListAdapter.onIncreaseProgressButtonClick = {
            viewModel.increaseCounterInTask(it)
        }
        return taskListAdapter
    }

    private fun settingUpSwitches() {
        binding.switchTasks.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.showTasks()
                binding.switchDoneTasks.isChecked = false
            } else {
                viewModel.hideTasks()
            }
        }

        binding.switchDoneTasks.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.showDoneTasks()
                binding.switchTasks.isChecked = false
            } else {
                viewModel.hideDoneTasks()
            }
        }
    }

    private fun launchEditFragment(taskId: Int) {
        parentFragmentManager.popBackStack()
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, SingleTaskFragment.newInstanceEditTask(taskId))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        fun newInstance() = TaskListFragment()

    }
}

