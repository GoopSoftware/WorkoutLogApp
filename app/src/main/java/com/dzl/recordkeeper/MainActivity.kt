package com.dzl.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.dzl.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_lifting -> onLiftingClicked()
            R.id.nav_cardio -> onCardioClicked()
            else -> return false
        }
        return true

    }
}