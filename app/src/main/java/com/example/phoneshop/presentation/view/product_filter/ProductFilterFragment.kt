package com.example.phoneshop.presentation.view.product_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentProductFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_product_filter, container, false)
        binding = FragmentProductFilterBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doneButton.setOnClickListener {
            dismiss()
        }
    }
}