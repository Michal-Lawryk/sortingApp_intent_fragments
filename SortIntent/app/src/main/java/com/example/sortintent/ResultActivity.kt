package com.example.sortintent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sortintent.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val unsortedNumbers = intent.getIntegerArrayListExtra("unsorted")?.toList() ?: emptyList()
        val sortType = intent.getStringExtra("sorted")

        val sortedNumbers = when (sortType) {
            "bubble" -> bubbleSort(unsortedNumbers)
            "insertion" -> insertionSort(unsortedNumbers)
            else -> unsortedNumbers
        }

        binding.textUnsortedNumbers.text = getString(R.string.unsorted_numbers, unsortedNumbers.joinToString(" "))
        binding.textSortedNumbers.text = getString(R.string.sorted_numbers, sortedNumbers.joinToString(" "))
    }

    private fun bubbleSort(numbers: List<Int>): List<Int> {
        val list = numbers.toMutableList()
        for (i in 0 until list.size - 1) {
            for (j in 0 until list.size - i - 1) {
                if (list[j] > list[j + 1]) {
                    val temp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = temp
                }
            }
        }
        return list
    }

    private fun insertionSort(numbers: List<Int>): List<Int> {
        val list = numbers.toMutableList()
        for (i in 1 until list.size) {
            val key = list[i]
            var j = i - 1
            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j]
                j--
            }
            list[j + 1] = key
        }
        return list
    }

    fun onBack(view: View) {
        finish()
    }
}
