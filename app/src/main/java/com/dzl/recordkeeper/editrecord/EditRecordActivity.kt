package com.dzl.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.dzl.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable


class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding



    private val screenData: ScreenData by lazy {
        @Suppress("DEPRECATION")
        intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
    }

    private val recordPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName,
            Context.MODE_PRIVATE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }


        return super.onOptionsItemSelected(item)
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFieldHint
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonClear.setOnClickListener {
            clearRecord()
        }
    }


    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY", null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCE_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        recordPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_RECORD_KEY", record)
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_DATE_KEY", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCE_DATE_KEY")
            finish()
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable


    companion object {
        const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

        const val SHARED_PREFERENCE_RECORD_KEY = "record"
        const val SHARED_PREFERENCE_DATE_KEY = "date"
    }

}

