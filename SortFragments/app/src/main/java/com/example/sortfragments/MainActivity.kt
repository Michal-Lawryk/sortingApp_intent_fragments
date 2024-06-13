package com.example.sortfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sortfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InputFragment.OnInputListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val inputFragment = InputFragment()
            inputFragment.setOnInputListener(this)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, inputFragment)
                .addToBackStack(null)  // Dodanie InputFragment do backstacku
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment is InputFragment) {
                fragment.clearInput()
            }
        }
    }

    override fun onInputSubmitted(numbers: List<Int>, sortType: String) {
        val sortedNumbers = when (sortType) {
            "bubble" -> bubbleSort(numbers)
            "insertion" -> insertionSort(numbers)
            else -> numbers
        }

        val resultFragment = ResultFragment.newInstance(numbers, sortedNumbers)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultFragment)
            .addToBackStack(null)  // Dodanie ResultFragment do backstacku
            .commit()
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
}
