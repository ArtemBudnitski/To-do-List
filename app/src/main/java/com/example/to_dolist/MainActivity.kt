package com.example.to_dolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<ListView>(R.id.listViewMain)
        val data: EditText = findViewById(R.id.user_data)
        val bttn = findViewById<Button>(R.id.button_on_me)

        val todo: MutableList<String> =
            mutableListOf()  // lista zadań do wykonania ktora jest pusta dzieki temu ze jest mutable
        val adapterek = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            todo
        ) // adapter do listy + zwykla defaultowa lista ; todo - lista zadań do wykonania krora jest wyzej
        list.adapter = adapterek // przypisanie adaptera do listy

        list.setOnItemClickListener { _, view, i, l ->
            val text = list.getItemAtPosition(i).toString()
            todo.removeAt(i) // usuwa element z listy
            adapterek.notifyDataSetChanged() // odswieza liste
            //OR
            //val text  = listView.getItemAtPosition(position).toString()
            //adapterek.remove(text)

            Toast.makeText(this, "Usunięto: $text ", Toast.LENGTH_SHORT).show()
        }

        bttn.setOnClickListener() {
            val text = data.text.toString().trim() // trim() - usuwa spacje z początku i końca
            if (text.isNotEmpty()) {
                adapterek.insert(text, 0) // dodaje element do listy
            } else {
                data.error = "Wpisz coś"
            }

        }


    }
}

