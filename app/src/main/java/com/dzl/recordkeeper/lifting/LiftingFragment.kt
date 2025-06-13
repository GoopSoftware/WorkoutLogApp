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

        binding.textViewSquatValue.text = liftingPreferences.getString("Squat ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewSquatDate.text = liftingPreferences.getString("Squat ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBenchPressValue.text = liftingPreferences.getString("Bench Press ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBenchPressDate.text = liftingPreferences.getString("Bench Press ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewDeadliftValue.text = liftingPreferences.getString("Dead lift ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewDeadliftDate.text = liftingPreferences.getString("Dead lift ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewOverheadPressValue.text = liftingPreferences.getString("Overhead Press ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewOverheadPressDate.text = liftingPreferences.getString("Overhead Press ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun setupClickListeners() {
        binding.containerSquat.setOnClickListener { launchLiftingRecordScreen("Squat") }
        binding.containerBenchPress.setOnClickListener { launchLiftingRecordScreen("Bench Press") }
        binding.containerDeadlift.setOnClickListener { launchLiftingRecordScreen("Dead lift") }
        binding.containerOverheadPress.setOnClickListener { launchLiftingRecordScreen("Overhead Press") }
    }

    private fun launchLiftingRecordScreen(lift: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(EditRecordActivity.INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(lift, FILENAME, "Pounds"))
        intent.putExtra("lift", lift)
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "lifting"

    }



}