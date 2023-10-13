package com.example.exchangerates.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerates.R
import com.example.exchangerates.status.LiveExchangeStatus
import com.example.exchangerates.view_model.LiveViewModel

class LiveFragment : Fragment() {

    private lateinit var viewModel: LiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_live, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showCourseButton = view.findViewById<Button>(R.id.showCurrentCourse)
        val courseText = view.findViewById<TextView>(R.id.Course)

        showCourseButton.setOnClickListener {
            viewModel.getLiveExchangeRates(
                source = "USD",
                currency = "RUB",
                format = 1
            )
        }
//        #1
//        viewModel.loadingStatus.observe(viewLifecycleOwner) { status ->
//            when (status) {
//                LiveExchangeStatus.Loading -> {
//
//                }
//                LiveExchangeStatus.Success -> {
//                  viewModel.data.observe(viewLifecycleOwner) { data ->
//                      courseText.text = data.quotes.rub.toString()
//                  }
//
//                }
//
//                else -> {}
//            }
//
//        }

//        #2
//        viewModel.data.observe(viewLifecycleOwner) { data ->
//            when (viewModel.loadingStatus.value) {
//                LiveExchangeStatus.Success -> {
//                    courseText.text = data?.quotes?.rub.toString()
//                }
//                else -> {
//                    // Можно добавить обработку других статусов, если необходимо
//                }
//            }
//        }

//      3#
        viewModel.data.observe(viewLifecycleOwner) { data ->
            courseText.text = data.quotes.rub.toString() + " руб."
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                LiveExchangeStatus.Loading -> { /* Тут может быть логика крутилки */ }
                else -> {}
            }
        }

    }
}