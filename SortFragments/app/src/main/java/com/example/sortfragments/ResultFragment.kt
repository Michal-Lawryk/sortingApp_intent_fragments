package com.example.sortfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sortfragments.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_UNSORTED_NUMBERS = "unsorted_numbers"
        private const val ARG_SORTED_NUMBERS = "sorted_numbers"

        fun newInstance(unsortedNumbers: List<Int>, sortedNumbers: List<Int>): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putIntegerArrayList(ARG_UNSORTED_NUMBERS, ArrayList(unsortedNumbers))
            args.putIntegerArrayList(ARG_SORTED_NUMBERS, ArrayList(sortedNumbers))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        val unsortedNumbers = arguments?.getIntegerArrayList(ARG_UNSORTED_NUMBERS)
        val sortedNumbers = arguments?.getIntegerArrayList(ARG_SORTED_NUMBERS)


        binding.textUnsortedNumbers.text = getString(R.string.unsorted_numbers, unsortedNumbers?.joinToString(" "))
        binding.textSortedNumbers.text = getString(R.string.sorted_numbers, sortedNumbers?.joinToString(" "))

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
