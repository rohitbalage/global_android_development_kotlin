package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.ExpandableListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.rrbofficial.androiduipracticekotlin.R

class MyExpandableListViewAdapter(
    private val context: Context,
    private val category: List<String>,
    private val names: Map<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return category.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return names[category[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return category[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return names[category[groupPosition]]?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.parent_list, null)
        }

        val txtParent = convertView!!.findViewById<TextView>(R.id.parent_list)
        txtParent.text = headerTitle
        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val childText = getChild(groupPosition, childPosition) as String

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child_list, null)
        }

        val txtChild = convertView!!.findViewById<TextView>(R.id.child_list)
        txtChild.text = childText
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
