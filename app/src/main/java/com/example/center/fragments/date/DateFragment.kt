package com.example.center.fragments.date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.center.R
import com.example.center.model.Date
import com.example.center.viewmodel.DateViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

class DateFragment : Fragment()
{
    private lateinit var mDateViewModel: DateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_date, container, false)

        // Recyclerview
        val adapter = DateAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // DateViewModel
        mDateViewModel = ViewModelProvider(this).get(DateViewModel::class.java)
        mDateViewModel.readAllData.observe(
            viewLifecycleOwner,
            Observer { date -> adapter.setData(date) })
        mDateViewModel.readAllData.observe(
            viewLifecycleOwner
        ) { list ->
            if (list.isNullOrEmpty())
            {
                presetDates()
            }
        }

        val floatingActionButton =
            view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener(
            {
                val date = Date(0, LocalDate.now().toString())

                mDateViewModel.addDate(date)
                Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            })

        return view
    }

    private fun presetDates()
    {
        mDateViewModel.addDate(Date(0, "2026-01-01"))
        mDateViewModel.addDate(Date(0, "2026-01-02"))
        mDateViewModel.addDate(Date(0, "2026-01-03"))
        mDateViewModel.addDate(Date(0, "2026-01-04"))
    }
}