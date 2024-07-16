package com.rrbofficial.androiduipracticekotlin.JetpackCompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rrbofficial.androiduipracticekotlin.databinding.FragmentNav1Binding
import com.rrbofficial.androiduipracticekotlin.R

/**
 * A simple [Fragment] subclass.
 * Use the [Nav1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Nav1Fragment : Fragment() {
    private lateinit var binding: FragmentNav1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle arguments if any
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nav1, container, false)

        // Set up the click listener for the submit button
        binding.submitbtn.setOnClickListener {
            val action = Nav1FragmentDirections.actionNav1FragmentToNav2Fragment()
            findNavController().navigate(action)
        }

        // Return the root view of the binding
        return binding.root
    }
}
