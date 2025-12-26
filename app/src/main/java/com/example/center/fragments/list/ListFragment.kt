package com.example.center.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.center.R
import com.example.center.model.Category
import com.example.center.model.Task
import com.example.center.viewmodel.CategoryViewModel
import com.example.center.viewmodel.DateViewModel
import com.example.center.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment()
{
    private val args by navArgs<ListFragmentArgs>()
    private lateinit var mCategoryViewModel: CategoryViewModel
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var mDateViewModel: DateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // TaskViewModel
        mCategoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mDateViewModel = ViewModelProvider(this).get(DateViewModel::class.java)
        mTaskViewModel.readAllData(args.currentDate.id).observe(
            viewLifecycleOwner,
            Observer { task -> adapter.setData(task, args.currentDate) })
        mCategoryViewModel.readAllData.observe(
            viewLifecycleOwner
        ) { list ->
            if (list.isNullOrEmpty())
            {
                presetCategories()
            }
        }


        val floatingActionButton =
            view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener(
            {
                val action = ListFragmentDirections.actionListFragmentToAddFragment(
                    args.currentDate
                )
                findNavController().navigate(action)
            })

        val backButton = view.findViewById<FloatingActionButton>(R.id.backButton)
        backButton.setOnClickListener(
            {
                findNavController().navigate(R.id.action_listFragment_to_DateFragment)
            })

        val deleteButton = view.findViewById<FloatingActionButton>(R.id.deleteButton)
        deleteButton.setOnClickListener(
            {
                deleteDate()
            })

        return view
    }

    private fun deleteDate()
    {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteDatedTasks(args.currentDate.id)
            mDateViewModel.deleteDate(args.currentDate)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentDate.date}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_listFragment_to_DateFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentDate.date}?")
        builder.setMessage("Are you sure you want to delete ${args.currentDate.date}?")
        builder.create().show()
    }

    private fun presetCategories()
    {
        mCategoryViewModel.addCategory(Category(0, "Work"))
        mCategoryViewModel.addCategory(Category(0, "Media"))
        mCategoryViewModel.addCategory(Category(0, "Life"))

        mTaskViewModel.addTask(Task(0, "Updated the UI for Center", "Work", 1))
        mTaskViewModel.addTask(Task(0, "Played Terraria", "Media", 1))
        mTaskViewModel.addTask(Task(0, "Played Super Mario Run", "Media", 1))

        mTaskViewModel.addTask(Task(0, "Watched Zootopia 2", "Media", 2))
        mTaskViewModel.addTask(Task(0, "Went to Hilltown", "Life", 2))

        mTaskViewModel.addTask(Task(0, "Center presentation", "Work", 3))
        mTaskViewModel.addTask(Task(0, "Went to see my doctor", "Life", 3))
        mTaskViewModel.addTask(Task(0, "Got my plane tickets", "Life", 3))

        mTaskViewModel.addTask(Task(0, "Fixed some issues in MKSS", "Work", 4))
        mTaskViewModel.addTask(Task(0, "Played Kirby Air Riders", "Media", 4))
        mTaskViewModel.addTask(Task(0, "Played Minecraft", "Media", 4))
    }
}