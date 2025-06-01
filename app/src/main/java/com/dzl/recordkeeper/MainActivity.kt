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
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset_cardio -> onResetCardioClicked()
            R.id.reset_lifting -> onResetLiftingClicked()
            R.id.reset_all -> onResetAllClicked()
            else -> return super.onOptionsItemSelected(item)

        }
        return true
    }

    private fun onResetCardioClicked() {
        Toast.makeText(this, "Cardio Clicked", Toast.LENGTH_LONG).show()
    }

    private fun onResetLiftingClicked() {
        Toast.makeText(this, "Lifting Clicked", Toast.LENGTH_LONG).show()
    }

    private fun onResetAllClicked() {
        Toast.makeText(this, "All Clicked", Toast.LENGTH_LONG).show()
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