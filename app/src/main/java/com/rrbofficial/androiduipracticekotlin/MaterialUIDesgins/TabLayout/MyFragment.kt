package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.TabLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rrbofficial.androiduipracticekotlin.R

class MyFragment : Fragment() {

    private lateinit var view: View
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_my, container, false)

        textView = view.findViewById(R.id.textView)
        val text = arguments?.getString("TEXT_ID")
        textView.text = text

        return view
    }
}
