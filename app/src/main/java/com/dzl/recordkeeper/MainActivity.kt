package com.dzl.recordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.dzl.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonLifting.setOnClickListener { onLiftingClicked() }
        binding.buttonCardio.setOnClickListener { onCardioClicked() }

    }

    private fun onCardioClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CardioFragment())
        }
    }

    private fun onLiftingClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, LiftingFragment())
        }
    }
}