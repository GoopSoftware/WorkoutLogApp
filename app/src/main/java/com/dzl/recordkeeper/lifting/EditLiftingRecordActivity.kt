package com.dzl.recordkeeper.lifting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dzl.recordkeeper.R

class EditLiftingRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_lifting_record)

        val lift = intent.getStringExtra("lift")
        title = "$lift Record"

    }
}