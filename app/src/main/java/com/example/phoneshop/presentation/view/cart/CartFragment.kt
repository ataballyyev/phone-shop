package com.example.phoneshop.presentation.view.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

    }
}