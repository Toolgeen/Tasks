package com.abdykadyr.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        val taskListAdapter = TaskListAdapter()
        binding.rvTaskList.adapter = taskListAdapter

        binding.switchTasks.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                viewModel.tasksList.invoke().observe(viewLifecycleOwner) {
                    taskListAdapter.submitList(it)
                }
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

