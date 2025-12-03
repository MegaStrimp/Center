package com.example.center.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.center.R
import com.example.center.model.Task
import com.example.center.viewmodel.TaskViewModel

class UpdateFragment : Fragment()
{
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var updateText_et: EditText
    private lateinit var update_btn: Button
    private lateinit var delete_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        updateText_et = view.findViewById(R.id.updateText_et)
        update_btn = view.findViewById(R.id.update_btn)
        delete_btn = view.findViewById(R.id.delete_btn)

        updateText_et.setText(args.currentTask.text)

        update_btn.setOnClickListener(
            {
                updateItem()
            })

        delete_btn.setOnClickListener(
            {
                deleteTask()
            })

        return view
    }

    private fun updateItem()
    {
        val text = updateText_et.text.toString()

        if (inputCheck(text))
        {
            val updatedTask = Task(args.currentTask.id, text, args.currentDate.id)
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()

            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment(
                args.currentDate
            )
            findNavController().navigate(action)
        } else
        {
            Toast.makeText(requireContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(text: String): Boolean
    {
        return !(TextUtils.isEmpty(text))
    }

    private fun deleteTask()
    {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentTask.id}",
                Toast.LENGTH_SHORT
            ).show()
            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment(
                args.currentDate
            )
            findNavController().navigate(action)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTask.id}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.id}?")
        builder.create().show()
    }
}