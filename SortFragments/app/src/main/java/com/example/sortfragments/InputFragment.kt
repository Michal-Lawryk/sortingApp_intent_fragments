package com.example.sortfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sortfragments.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    interface OnInputListener {
        fun onInputSubmitted(numbers: List<Int>, sortType: String)
    }

    private var listener: OnInputListener? = null
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    fun setOnInputListener(listener: OnInputListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val description = when (checkedId) {
                R.id.radio_bubble_sort -> "Sortowanie bąbelkowe: Prosty algorytm sortowania oparty na porównaniach."
                R.id.radio_insertion_sort -> "Sortowanie przez wstawianie: Tworzy ostateczną posortowaną tablicę po jednym elemencie na raz."
                else -> ""
            }
            binding.textSortDescription.text = description
        }

        binding.buttonSubmit.setOnClickListener {
            val inputText = binding.inputNumbers.text.toString()
            val numbers = inputText.split(",").mapNotNull { it.trim().toIntOrNull() }

            if (numbers.isEmpty()) {
                Toast.makeText(context, "Wprowadź liczby oddzielone przecinkami", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sortType = if (binding.radioBubbleSort.isChecked) "bubble" else "insertion"
            listener?.onInputSubmitted(numbers, sortType)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun clearInput() {
        binding.inputNumbers.text.clear()
    }
}
