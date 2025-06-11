package com.dzl.recordkeeper.cardio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dzl.recordkeeper.databinding.FragmentCardioBinding
import com.dzl.recordkeeper.editrecord.EditRecordActivity
import com.dzl.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CardioFragment : Fragment() {

    private lateinit var binding: FragmentCardioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardioBinding.inflate(inflater, container, false)
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


    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchRunningRecordScreen("5km") }
        binding.container10km.setOnClickListener { launchRunningRecordScreen("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningRecordScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRunningRecordScreen("Marathon") }
    }


    private fun displayRecords() {

        val runningPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("5km record", null)
        binding.textView5kmDate.text =  runningPreferences.getString("5km date", null)
        binding.textView10kmValue.text = runningPreferences.getString("10km record", null)
        binding.textView10kmDate.text =  runningPreferences.getString("10km date", null)
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text =  runningPreferences.getString("Half Marathon date", null)
        binding.textViewMarathonValue.text = runningPreferences.getString("Marathon record", null)
        binding.textViewMarathonDate.text =  runningPreferences.getString("Marathon date", null)
    }


    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(distance, FILENAME, "Time"))
        startActivity(intent)
    }

    companion object {
        const val FILENAME = "cardio"
    }

}