package com.example.phoneshop.presentation.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        val navHost = (childFragmentManager.findFragmentById(R.id.fragmentContainer)) as NavHostFragment
        val navController = navHost.navController

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.action_detailsFragment_to_homeFragment)
                    true
                }
                R.id.shoppingFragment -> {
                    navController.navigate(R.id.action_homeFragment_to_detailsFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }

    }

}