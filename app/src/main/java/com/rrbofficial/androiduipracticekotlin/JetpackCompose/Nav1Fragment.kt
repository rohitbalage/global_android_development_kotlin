package com.rrbofficial.androiduipracticekotlin.JetpackCompose

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

//        val bundle = bundleOf("user_input" to binding.editTextText.text.toString())

        // Set up the click listener for the submit button
        binding.submitbtn.setOnClickListener {

            if(!TextUtils.isEmpty(binding.editTextText.text.toString()))
            {
                val userInput = binding.editTextText.text.toString()
                val action = Nav1FragmentDirections.actionNav1FragmentToNav2Fragment(userInput)
                findNavController().navigate(action)
            }
            else
            {
                Toast.makeText(activity ,"Please enter a text input", Toast.LENGTH_SHORT).show()
            }

//            it.findNavController().navigate(R.id.action_nav1Fragment_to_nav2Fragment)
        }

        // Return the root view of the binding
        return binding.root
    }
}
