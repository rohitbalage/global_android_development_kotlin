package com.rrbofficial.androiduipracticekotlin.SQLite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R

class SQLiteForm : AppCompatActivity(), View.OnClickListener {

    private lateinit var db: DatabaseHandler
    private lateinit var eId: EditText
    private lateinit var eName: EditText
    private lateinit var eAge: EditText
    private lateinit var eCity: EditText
    private lateinit var bSave: Button
    private lateinit var bView: Button
    private lateinit var bUpdate: Button
    private lateinit var bDelete: Button
    private lateinit var bSearch: Button

    private var id: String = ""
    private var name: String = ""
    private var age: String = ""
    private var city: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_form)

        eId = findViewById(R.id.id)
        eName = findViewById(R.id.name)
        eAge = findViewById(R.id.age)
        eCity = findViewById(R.id.city)

        bSave = findViewById(R.id.save)
        bView = findViewById(R.id.view)
        bUpdate = findViewById(R.id.update)
        bDelete = findViewById(R.id.delete)
        bSearch = findViewById(R.id.search)

        bSave.setOnClickListener(this)
        bView.setOnClickListener(this)
        bUpdate.setOnClickListener(this)
        bDelete.setOnClickListener(this)
        bSearch.setOnClickListener(this)

        db = DatabaseHandler(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.save -> {
                name = eName.text.toString()
                age = eAge.text.toString()
                city = eCity.text.toString()

                if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
                    Toast.makeText(this, "Please fill the fields.", Toast.LENGTH_SHORT).show()
                } else {
                    db.addEmployee(name, age, city)
                    eId.setText("")
                    eName.setText("")
                    eAge.setText("")
                    eCity.setText("")
                    Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.view -> {
                val data = db.getEmployee()
                if (data.isEmpty()) {
                    Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, SQLite_result::class.java)
                    startActivity(intent)
                }
            }
            R.id.update -> {
                id = eId.text.toString().trim()
                name = eName.text.toString().trim()
                age = eAge.text.toString()
                city = eCity.text.toString()

                if (id.isEmpty() || name.isEmpty() || age.isEmpty() || city.isEmpty()) {
                    Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show()
                } else {
                    val l = id.toLong()
                    db.updateEmployee(l, name, age, city)
                    eId.setText("")
                    eName.setText("")
                    eAge.setText("")
                    eCity.setText("")
                    Toast.makeText(this, "Data updated successfully.", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.delete -> {
                id = eId.text.toString()
                if (id.isEmpty()) {
                    Toast.makeText(this, "Please fill the Id.", Toast.LENGTH_SHORT).show()
                } else {
                    val l = id.toLong()
                    db.deleteEmployee(l)
                    eId.setText("")
                    eName.setText("")
                    eAge.setText("")
                    eCity.setText("")
                    Toast.makeText(this, "Data deleted successfully.", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.search -> {
                id = eId.text.toString().trim()
                if (id.isEmpty()) {
                    Toast.makeText(this, "Please fill the Id.", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val l1 = id.toLong()
                        name = db.getName(l1) ?: ""
                        age = db.getAge(l1) ?: ""
                        city = db.getCity(l1) ?: ""
                        eName.setText(name)
                        eAge.setText(age)
                        eCity.setText(city)
                        Toast.makeText(this, "Data found successfully.", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(this, "ID is not valid.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
