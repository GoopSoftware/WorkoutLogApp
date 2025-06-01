package com.dzl.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dzl.recordkeeper.databinding.FragmentLiftingBinding

class LiftingFragment : Fragment(){

    private lateinit var binding: FragmentLiftingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiftingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.containerSquat.setOnClickListener { launchLiftingRecordScreen("Squat") }
        binding.containerBenchPress.setOnClickListener { launchLiftingRecordScreen("Bench Press") }
        binding.containerDeadlift.setOnClickListener { launchLiftingRecordScreen("Deadlift") }
        binding.containerOverheadPress.setOnClickListener { launchLiftingRecordScreen("Overhead Press") }
    }

    private fun launchLiftingRecordScreen(lift: String) {
        val intent = Intent(context, EditLiftingRecordActivity::class.java)
        intent.putExtra("lift", lift)
        startActivity(intent)
    }

}