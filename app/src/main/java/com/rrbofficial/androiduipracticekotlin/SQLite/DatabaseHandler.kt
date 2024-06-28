package com.rrbofficial.androiduipracticekotlin.SQLite
    import android.content.ContentValues
    import android.content.Context
    import android.database.Cursor
    import android.database.sqlite.SQLiteDatabase
    import android.database.sqlite.SQLiteOpenHelper

    class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        private val db: SQLiteDatabase
            get() = writableDatabase

        override fun onCreate(db: SQLiteDatabase) {
            val query = "CREATE TABLE $TABLE_EMPLOYEE ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT, $KEY_AGE TEXT, $KEY_CITY TEXT)"
            db.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEE")
            onCreate(db)
        }

        fun addEmployee(name: String, age: String, city: String): Long {
            val values = ContentValues().apply {
                put(KEY_NAME, name)
                put(KEY_AGE, age)
                put(KEY_CITY, city)
            }
            return db.insert(TABLE_EMPLOYEE, null, values)
        }

        fun getEmployee(): String {
            val cursor = db.query(TABLE_EMPLOYEE, arrayOf(KEY_ID, KEY_NAME, KEY_AGE, KEY_CITY), null, null, null, null, null)
            val res = buildString {
                cursor.use {
                    while (cursor.moveToNext()) {
                        append("Id: ${cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID))}\n")
                        append("Name: ${cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME))}\n")
                        append("Age: ${cursor.getString(cursor.getColumnIndexOrThrow(KEY_AGE))}\n")
                        append("City: ${cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY))}\n\n")
                    }
                }
            }
            db.close()
            return res
        }

        fun updateEmployee(l: Long, name: String, age: String, city: String) {
            val values = ContentValues().apply {
                put(KEY_NAME, name)
                put(KEY_AGE, age)
                put(KEY_CITY, city)
            }
            db.update(TABLE_EMPLOYEE, values, "$KEY_ID=$l", null)
            db.close()
        }

        fun deleteEmployee(l: Long) {
            db.delete(TABLE_EMPLOYEE, "$KEY_ID=$l", null)
        }

        fun getName(l1: Long): String? {
            return db.query(TABLE_EMPLOYEE, arrayOf(KEY_ID, KEY_NAME, KEY_AGE, KEY_CITY), "$KEY_ID=$l1", null, null, null, null).use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)) else null
            }
        }

        fun getAge(l1: Long): String? {
            return db.query(TABLE_EMPLOYEE, arrayOf(KEY_ID, KEY_NAME, KEY_AGE, KEY_CITY), "$KEY_ID=$l1", null, null, null, null).use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(cursor.getColumnIndexOrThrow(KEY_AGE)) else null
            }
        }

        fun getCity(l1: Long): String? {
            return db.query(TABLE_EMPLOYEE, arrayOf(KEY_ID, KEY_NAME, KEY_AGE, KEY_CITY), "$KEY_ID=$l1", null, null, null, null).use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY)) else null
            }
        }

        companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "employeeManager"
            private const val TABLE_EMPLOYEE = "employee"
            private const val KEY_ID = "id"
            private const val KEY_NAME = "name"
            private const val KEY_AGE = "age"
            private const val KEY_CITY = "city"
        }
    }
