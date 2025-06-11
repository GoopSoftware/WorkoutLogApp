package com.dzl.recordkeeper.lifting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dzl.recordkeeper.databinding.FragmentLiftingBinding
import com.dzl.recordkeeper.editrecord.EditRecordActivity
import com.dzl.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

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

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val liftingPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textViewSquatValue.text = liftingPreferences.getString("Squat record", null)
        binding.textViewSquatDate.text = liftingPreferences.getString("Squat date", null)
        binding.textViewBenchPressValue.text = liftingPreferences.getString("Bench Press record", null)
        binding.textViewBenchPressDate.text = liftingPreferences.getString("Bench Press date", null)
        binding.textViewDeadliftValue.text = liftingPreferences.getString("Dead lift record", null)
        binding.textViewDeadliftDate.text = liftingPreferences.getString("Dead lift date", null)
        binding.textViewOverheadPressValue.text = liftingPreferences.getString("Overhead Press record", null)
        binding.textViewOverheadPressDate.text = liftingPreferences.getString("Overhead Press date", null)
    }

    private fun setupClickListeners() {
        binding.containerSquat.setOnClickListener { launchLiftingRecordScreen("Squat") }
        binding.containerBenchPress.setOnClickListener { launchLiftingRecordScreen("Bench Press") }
        binding.containerDeadlift.setOnClickListener { launchLiftingRecordScreen("Dead lift") }
        binding.containerOverheadPress.setOnClickListener { launchLiftingRecordScreen("Overhead Press") }
    }

    private fun launchLiftingRecordScreen(lift: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(lift, FILENAME, "Pounds"))
        intent.putExtra("lift", lift)
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "lifting"
    }

}