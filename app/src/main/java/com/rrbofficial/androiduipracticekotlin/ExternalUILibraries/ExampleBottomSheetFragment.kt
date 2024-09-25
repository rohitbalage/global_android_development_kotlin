package com.rrbofficial.androiduipracticekotlin.ExternalUILibraries

import android.graphics.Color
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.rrbofficial.androiduipracticekotlin.R

class ExampleBottomSheetFragment : SuperBottomSheetFragment() {

    override fun getPeekHeight(): Int {
        // Set the collapsed height of the bottom sheet
        return 300  // 300px when collapsed
    }

    override fun getDim(): Float {
        // Set the background dim level between 0 (no dim) to 1 (full dim)
        return 0.5f  // 50% dim on background
    }

    override fun getBackgroundColor(): Int {
        // Set the background color of the bottom sheet
        return Color.WHITE  // White background
    }

    override fun getStatusBarColor(): Int {
        // Set the status bar color when the bottom sheet is expanded
        return Color.DKGRAY  // Dark gray status bar color
    }

    override fun getCornerRadius(): Float {
        // Set the corner radius of the bottom sheet
        return 50f  // 50dp corner radius
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        // Specify if the sheet should skip the collapsed state and always be expanded
        return false  // Allow collapsed state
    }

    override fun isSheetCancelableOnTouchOutside(): Boolean {
        // Specify if the bottom sheet should be cancelable when tapping outside
        return true  // Allow cancel on touch outside
    }

    override fun isSheetCancelable(): Boolean {
        // Specify if the bottom sheet can be canceled by pressing the back button
        return true  // Allow cancelable with back press
    }

    override fun animateCornerRadius(): Boolean {
        // Specify if the corner radius should animate when expanding/collapsing
        return true  // Animate corner radius
    }

    override fun animateStatusBar(): Boolean {
        // Specify if the status bar color should animate when expanding/collapsing
        return true  // Animate status bar color change
    }

    override fun getExpandedHeight(): Int {
        // Set the expanded height of the bottom sheet
        return -1  // Match parent (full screen height)
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_bottom_sheet_external, container, false)
    }
}
