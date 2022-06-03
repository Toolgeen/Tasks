package com.abdykadyr.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdykadyr.tasks.databinding.FragmentTaskListBinding
import java.lang.RuntimeException

class TaskListFragment: Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding: FragmentTaskListBinding
    get() = _binding ?: throw RuntimeException("FragmentTaskListBinding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}