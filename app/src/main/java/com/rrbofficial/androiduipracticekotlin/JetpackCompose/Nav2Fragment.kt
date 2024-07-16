package com.rrbofficial.androiduipracticekotlin.JetpackCompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.FragmentNav2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Nav2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Nav2Fragment : Fragment() {

    private lateinit var binding: FragmentNav2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle any necessary setup
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nav2, container, false)

        // Retrieve the argument passed from Nav1Fragment
        val userInput = arguments?.getString("user_input")

        // Use the retrieved argument to set the text of the TextView
        binding.showUserInput.text = userInput

        // Return the root view of the binding
        return binding.root
    }
}
