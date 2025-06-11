package com.dzl.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.dzl.recordkeeper.cardio.CardioFragment
import com.dzl.recordkeeper.databinding.ActivityMainBinding
import com.dzl.recordkeeper.lifting.LiftingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar



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
        val menuClickHandled = when (item.itemId) {
            R.id.reset_cardio -> {
                showConfirmationDialogue(CARDIO_DISPLAY_VALUE)
            }

            R.id.reset_lifting -> {
                showConfirmationDialogue(LIFTING_DISPLAY_VALUE)
            }

            R.id.reset_all -> {
                showConfirmationDialogue(ALL_DISPLAY_VALUE)
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        return menuClickHandled
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_cardio -> {
                onCardioClicked()
            }

            R.id.nav_lifting -> {
                onLiftingClicked()
            }

            else -> {}
        }
    }

    private fun showConfirmationDialogue(selection: String): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection records")
            .setMessage("Are you sure you want to clear the records?")
            .setPositiveButton("Yes") { _, _ ->
                when (selection) {
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(LiftingFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(LiftingFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                    }
                    else -> getSharedPreferences(selection, MODE_PRIVATE).edit { clear() }
                }
                refreshCurrentFragment()
                val confirmationBar = Snackbar.make(binding.frameContent, "Records cleared successfully!", Snackbar.LENGTH_LONG)
                confirmationBar.anchorView = binding.bottomNav
                confirmationBar.show()
            }
            .setNegativeButton("No", null)
            .show()
        return true
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

    companion object {
        const val CARDIO_DISPLAY_VALUE = "cardio"
        const val LIFTING_DISPLAY_VALUE = "lifting"
        const val ALL_DISPLAY_VALUE = "all"
    }
}