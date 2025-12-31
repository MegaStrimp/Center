package com.example.center.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.center.R
import com.example.center.model.Task
import com.example.center.viewmodel.CategoryViewModel
import com.example.center.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddFragment : Fragment()
{
    private val args by navArgs<AddFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var mCategoryViewModel: CategoryViewModel
    private lateinit var addText_et: EditText
    private lateinit var add_btn: Button
    private lateinit var categories: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mCategoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        addText_et = view.findViewById(R.id.addText_et)
        add_btn = view.findViewById(R.id.add_btn)
        categories = view.findViewById(R.id.categories)

        add_btn.setOnClickListener(
            {
                insertDataToDatabase()
            })

        val backButton = view.findViewById<FloatingActionButton>(R.id.backButton)
        backButton.setOnClickListener(
            {
                val action = AddFragmentDirections.actionAddFragmentToListFragment(
                    args.currentDate
                )

                findNavController().navigate(action)
            })

        mCategoryViewModel.readAllData.observe(viewLifecycleOwner) { categoryList ->
            val titles = categoryList.map { it.title }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                titles
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categories.adapter = adapter
        }

        return view
    }

    private fun insertDataToDatabase()
    {
        val text = addText_et.text.toString()

        if (inputCheck(text))
        {
            val task = Task(0, text, categories.selectedItem.toString(), args.currentDate.id)

            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

            val action = AddFragmentDirections.actionAddFragmentToListFragment(
                args.currentDate
            )

            findNavController().navigate(action)
        } else
        {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(text: String): Boolean
    {
        return !(TextUtils.isEmpty(text))
    }
}