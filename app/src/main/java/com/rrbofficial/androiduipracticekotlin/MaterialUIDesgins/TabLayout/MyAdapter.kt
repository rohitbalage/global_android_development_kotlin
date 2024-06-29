package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.TabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val arrayList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        val myFragment = MyFragment()
        val bundle = Bundle()
        bundle.putString("TEXT_ID", arrayList[position])
        myFragment.arguments = bundle
        return myFragment
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    fun addData() {
        arrayList.add("CHATS")
        arrayList.add("STATUS")
        arrayList.add("CALLS")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayList[position]
    }
}
