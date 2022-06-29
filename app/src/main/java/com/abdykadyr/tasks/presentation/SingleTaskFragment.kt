package com.abdykadyr.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abdykadyr.tasks.R
import com.abdykadyr.tasks.databinding.FragmentSingleTaskBinding
import com.abdykadyr.tasks.domain.entities.Task
import java.time.Duration

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
        _binding = FragmentSingleTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingUpListeners()
        observeViewModel()
        settingUpSpinner()

    }

    private fun settingUpSpinner() {
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.spinner_values,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerCategories.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.requiredDeadline.observe(viewLifecycleOwner) {
            settingUpDeadlineViews(it)
        }
        viewModel.requiredTimer.observe(viewLifecycleOwner) {
            settingUpTimerViews(it)
        }
        viewModel.requiredRepeatsCounter.observe(viewLifecycleOwner) {
            binding.switchRepeats.setOnCheckedChangeListener { _, b ->
                if (b) {
                    viewModel.turnOnRepeatsCounter()
                } else {
                    viewModel.turnOffRepeatsCounter()
                    binding.etRepeats.setText(R.string.base_count_of_repeats)
                }
            }
            settingUpRepeatsViews(it)
        }
    }

    private fun settingUpListeners() {
        binding.switchDeadline.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewModel.turnOnDeadline()
            } else {
                viewModel.turnOffDeadline()
            }
        }


        binding.switchTimer.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewModel.turnOnTimer()
            } else {
                viewModel.turnOffTimer()
            }
        }
        binding.etTime.setOnClickListener { showTimePickerDialog(it) }
        binding.etDate.setOnClickListener { showDatePickerDialog(it) }

        binding.buttonConfirm.setOnClickListener {
            if (isInputTaskValid()) {
                viewModel.addTask(
                    binding.etDescription.text.toString(),
                    binding.spinnerCategories.selectedItem.toString(),
                    binding.etDate.text.toString(),
                    binding.etTime.text.toString(),
                    binding.etRepeats.text.toString().toInt()
                )
            } else {
                Toast.makeText(requireContext(),"Input is not valid",LENGTH_SHORT).show()
            }
        }
    }

    private fun isInputTaskValid(): Boolean {
        return !(binding.etDescription.text.toString()
            .isEmpty() || binding.etRepeats.text.toString()
            .isEmpty())
    }

    private fun settingUpTimerViews(isTimerRequired: Boolean) {
        if (isTimerRequired) {
            binding.tvTimerTitle.visibility = View.VISIBLE
            binding.etTimer.visibility = View.VISIBLE

        } else {
            binding.tvTimerTitle.visibility = View.GONE
            binding.etTimer.visibility = View.GONE
        }
    }

    private fun settingUpRepeatsViews(isRepeatsRequired: Boolean) {
        if (isRepeatsRequired) {
            binding.etRepeats.visibility = View.VISIBLE

        } else {
            binding.etRepeats.visibility = View.GONE
        }
    }

    private fun settingUpDeadlineViews(isDeadlineRequired: Boolean) {
        if (isDeadlineRequired) {
            with(binding) {
                tvDeadlineTitle.visibility = View.VISIBLE
                etTime.visibility = View.VISIBLE
                etDate.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                tvDeadlineTitle.visibility = View.GONE
                etTime.visibility = View.GONE
                etDate.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //parse params from List
    //can be task for editing
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

    fun showTimePickerDialog(v: View) {
        val fragment = TimePickerFragment()
        fragment.onSetListener = { binding.etTime.setText(it) }
        fragment.show(parentFragmentManager, "timePicker")
    }

    fun showDatePickerDialog(v: View) {
        val fragment = DatePickerFragment()
        fragment.onSetListener = { binding.etDate.setText(it) }
        fragment.show(parentFragmentManager, "datePicker")
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

        fun newInstanceEditTask(taskId: Int): SingleTaskFragment {
            return SingleTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(TASK_ID, taskId)
                }
            }
        }
    }

}