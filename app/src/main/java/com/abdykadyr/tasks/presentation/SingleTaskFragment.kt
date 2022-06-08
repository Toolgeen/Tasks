package com.abdykadyr.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abdykadyr.tasks.databinding.FragmentSingleTaskBinding
import com.abdykadyr.tasks.domain.entities.Task

class SingleTaskFragment : Fragment() {

    private var screenMode = MODE_UNKNOWN
    private var taskId = Task.ID_UNDEFINED

    private val viewModel by lazy {
        ViewModelProvider(this)[SingleTaskViewModel::class.java]
    }

    private var _binding: FragmentSingleTaskBinding? = null
    private val binding: FragmentSingleTaskBinding
    get() = _binding ?: throw RuntimeException("FragmentSingleTaskBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(TASK_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            taskId = args.getInt(TASK_ID)
        }
    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val TASK_ID = "extra_TASK_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddTask(): SingleTaskFragment {
            return SingleTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditTask(taskId : Int): SingleTaskFragment {
            return SingleTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(TASK_ID, taskId)
                }
            }
        }
    }

}