package com.example.builderslog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        val navController: NavController = Navigation.findNavController(this, R.id.fragment_host)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    override fun onBackPressed() {
        // exit alert dialog (prompt).
                val builder = MaterialAlertDialogBuilder(this)
                builder.setTitle("Exit application")
                builder.setMessage("Are you sure to quit application")
                builder.setPositiveButton("Yes"){ dialog, which ->
                    moveTaskToBack(true)
                    android.os.Process.killProcess(android.os.Process.myPid())
                    System.exit(1)
                }
                builder.setNegativeButton("Cancel"){ dialog, which ->
                    // Do nothing.
                }
                builder.show()
    }
}