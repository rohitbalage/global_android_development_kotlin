package com.rrbofficial.androiduipracticekotlin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class AndroidUIWidgets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_uiwidgets)
        // Binding the UI components
        val buttonInAndroidUI: TextView = findViewById(R.id.buttonInAndroidUI)
        val redImageBtn: ImageButton = findViewById(R.id.redimagebtn)
        val rGroup: RadioGroup = findViewById(R.id.rgroup)
        val rButton1: RadioButton = findViewById(R.id.rbutton1)
        val rButton2: RadioButton = findViewById(R.id.rbutton2)
        val submitRadioButtonUi: Button = findViewById(R.id.submitRadioButtonUi)
        val toggle: ToggleButton = findViewById(R.id.toggle)
        val image: ImageView = findViewById(R.id.image)
        val check1: CheckBox = findViewById(R.id.check1)
        val check2: CheckBox = findViewById(R.id.check2)
        val check3: CheckBox = findViewById(R.id.check3)
        val submitCheckboxesUI: Button = findViewById(R.id.submitCheckboxesUI)
        val switchButtonCheckboxesUI: Switch = findViewById(R.id.switchbuttonCheckboxesUI)
        val progress: ProgressBar = findViewById(R.id.progress)
        val ratingUI: RatingBar = findViewById(R.id.ratingUI)
        val submitRatingBarUI: Button = findViewById(R.id.submitRatingBarUI)
        val spinnerUI: Spinner = findViewById(R.id.spinnerUI)
        val seekbarUI: SeekBar = findViewById(R.id.seekbarUI)
        val textViewSeekBarUI: TextView = findViewById(R.id.textViewSeekBarUI)
        val checkedTextviewUI: CheckedTextView = findViewById(R.id.checkedtextviewUI)
        val textSwitcherUI: TextSwitcher = findViewById(R.id.textswitcherUI)
        val scrollView: ScrollView = findViewById(R.id.scrollview)

        // Binding the new UI components
        val showDatePicker: Button = findViewById(R.id.showDatePicker)
        val showTimePicker: Button = findViewById(R.id.showTimePicker)
        val webView: WebView = findViewById(R.id.webView)
        val searchView: SearchView = findViewById(R.id.searchView)
        val switchCompat: SwitchCompat = findViewById(R.id.switchCompat)

        // Setting onClick listeners with Toast messages
        buttonInAndroidUI.setOnClickListener {
            Toast.makeText(this, "TextView Clicked", Toast.LENGTH_SHORT).show()
        }

        redImageBtn.setOnClickListener {
            Toast.makeText(this, "ImageButton Clicked", Toast.LENGTH_SHORT).show()
        }

        rGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioText = when (checkedId) {
                R.id.rbutton1 -> "Radio 1 Selected"
                R.id.rbutton2 -> "Radio 2 Selected"
                else -> ""
            }
            Toast.makeText(this, radioText, Toast.LENGTH_SHORT).show()
        }

        submitRadioButtonUi.setOnClickListener {
            val selectedId = rGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "No RadioButton Selected", Toast.LENGTH_SHORT).show()
            } else {
                val radioText = findViewById<RadioButton>(selectedId).text.toString()
                Toast.makeText(this, "Selected: $radioText", Toast.LENGTH_SHORT).show()
            }
        }

        toggle.setOnClickListener {
            val isChecked = toggle.isChecked
            Toast.makeText(
                this,
                "ToggleButton is ${if (isChecked) "ON" else "OFF"}",
                Toast.LENGTH_SHORT
            ).show()
        }

        image.setOnClickListener {
            Toast.makeText(this, "ImageView Clicked", Toast.LENGTH_SHORT).show()
        }

        submitCheckboxesUI.setOnClickListener {
            val sb = StringBuilder("Selected: ")
            if (check1.isChecked) sb.append("Apple ")
            if (check2.isChecked) sb.append("Orange ")
            if (check3.isChecked) sb.append("Mango ")
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show()
        }

        switchButtonCheckboxesUI.setOnClickListener {
            val isChecked = switchButtonCheckboxesUI.isChecked
            Toast.makeText(this, "Switch is ${if (isChecked) "ON" else "OFF"}", Toast.LENGTH_SHORT)
                .show()
        }

        submitRatingBarUI.setOnClickListener {
            val rating = ratingUI.rating
            Toast.makeText(this, "Rating: $rating", Toast.LENGTH_SHORT).show()
        }

        seekbarUI.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textViewSeekBarUI.text = "Progress: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(this@AndroidUIWidgets, "SeekBar Touch Started", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(this@AndroidUIWidgets, "SeekBar Touch Stopped", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        checkedTextviewUI.setOnClickListener {
            checkedTextviewUI.toggle()
            Toast.makeText(
                this,
                "CheckedTextView Clicked, isChecked: ${checkedTextviewUI.isChecked}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // DatePicker
        showDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                    Toast.makeText(
                        this,
                        "Selected Date: $selectedDay/${selectedMonth + 1}/$selectedYear",
                        Toast.LENGTH_SHORT
                    ).show()
                }, year, month, day)
            datePickerDialog.show()
        }

        // TimePicker
        showTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                Toast.makeText(
                    this,
                    "Selected Time: $selectedHour:$selectedMinute",
                    Toast.LENGTH_SHORT
                ).show()
            }, hour, minute, true)
            timePickerDialog.show()
        }

        // WebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com")

        // SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@AndroidUIWidgets, "Search Query: $query", Toast.LENGTH_SHORT)
                    .show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val materialButton: MaterialButton = findViewById(R.id.materialButton)
        materialButton.setOnClickListener {
            Toast.makeText(this, "Material Button Clicked", Toast.LENGTH_SHORT).show()
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "FAB Clicked", Toast.LENGTH_SHORT).show()
        }

        val cardView: CardView = findViewById(R.id.cardView)
        cardView.setOnClickListener {
            Toast.makeText(this, "CardView Clicked", Toast.LENGTH_SHORT).show()
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@AndroidUIWidgets, "Selected: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Another interface callback
            }
        }
        switchCompat.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this@AndroidUIWidgets, "Compatct Switch: ON", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Switch is off
                Toast.makeText(this@AndroidUIWidgets, "Compatct Switch: OFF", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
