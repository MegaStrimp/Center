package com.example.center.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.center.R
import com.example.center.model.User
import com.example.center.viewmodel.UserViewModel

class UpdateFragment : Fragment()
{
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var updateFirstName_et: EditText
    private lateinit var updateLastName_et: EditText
    private lateinit var updateAge_et: EditText
    private lateinit var update_btn: Button
    private lateinit var delete_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        updateFirstName_et = view.findViewById(R.id.updateFirstName_et)
        updateLastName_et = view.findViewById(R.id.updateLastName_et)
        updateAge_et = view.findViewById(R.id.updateAge_et)
        update_btn = view.findViewById(R.id.update_btn)
        delete_btn = view.findViewById(R.id.delete_btn)

        updateFirstName_et.setText(args.currentUser.firstName)
        updateLastName_et.setText(args.currentUser.lastName)
        updateAge_et.setText(args.currentUser.age.toString())

        update_btn.setOnClickListener(
        {
            updateItem()
        })

        delete_btn.setOnClickListener(
        {
            deleteUser()
        })

        return view
    }

    private fun updateItem()
    {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(firstName, lastName, updateAge_et.text))
        {
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    private fun deleteUser()
    {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}