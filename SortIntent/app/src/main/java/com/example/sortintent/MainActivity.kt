package com.example.sortintent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumbersEditText: EditText
    private lateinit var radioBubbleSort: RadioButton
    private lateinit var radioInsertionSort: RadioButton
    private lateinit var textSortDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumbersEditText = findViewById(R.id.input_numbers)
        radioBubbleSort = findViewById(R.id.radio_bubble_sort)
        radioInsertionSort = findViewById(R.id.radio_insertion_sort)
        textSortDescription = findViewById(R.id.text_sort_description)

        radioBubbleSort.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textSortDescription.text =
                    "Sortowanie bąbelkowe: Prosty algorytm sortowania oparty na porównaniach."
            }
        }

        radioInsertionSort.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textSortDescription.text =
                    "Sortowanie przez wstawianie: Tworzy ostateczną posortowaną tablicę po jednym elemencie na raz."
            }

            val buttonSubmit = findViewById<Button>(R.id.button_submit)
            buttonSubmit.setOnClickListener {
                val inputText = inputNumbersEditText.text.toString()
                val numbers = inputText.split(",").mapNotNull { it.trim().toIntOrNull() }

                if (numbers.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Wprowadź liczby oddzielone przecinkami",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val sortType = if (radioBubbleSort.isChecked) "bubble" else "insertion"
                val intent = Intent(this, ResultActivity::class.java)
                intent.putIntegerArrayListExtra("unsorted", ArrayList(numbers))
                intent.putExtra("sorted", sortType)
                startActivity(intent)
            }
        }
    }
}