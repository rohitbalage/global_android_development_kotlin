package com.rrbofficial.androiduipracticekotlin.SQLite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R

class SQLite_result : AppCompatActivity() {
    private lateinit var db: DatabaseHandler
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_result)

        textView = findViewById(R.id.viewSavedData)
        db = DatabaseHandler(this)

        val data = db.getEmployee()
        textView.text = data
    }
}
