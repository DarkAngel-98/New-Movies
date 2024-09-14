package com.solo.mymovies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.solo.mymovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainNavController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? NavHostFragment
        navHostFragment?.apply {
            this@MainActivity.mainNavController = navController
        }
        mainNavController.graph =
            mainNavController.navInflater.inflate(R.navigation.main_navigation)

        binding.bottomNav.apply {
            itemIconTintList = null
            setupWithNavController(mainNavController)
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, mainNavController)
                return@setOnItemSelectedListener true
            }
        }
        setContentView(binding.root)
    }
}