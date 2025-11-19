package com.example.center.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.center.R
import com.example.center.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>()
{
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int
    {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val id_txt = holder.itemView.findViewById<TextView>(R.id.id_txt)
        val firstName_txt = holder.itemView.findViewById<TextView>(R.id.firstName_txt)
        val lastName_txt = holder.itemView.findViewById<TextView>(R.id.lastName_txt)
        val age_txt = holder.itemView.findViewById<TextView>(R.id.age_txt)

        val currentItem = userList[position]

        id_txt.text = currentItem.id.toString()
        firstName_txt.text = currentItem.firstName
        lastName_txt.text = currentItem.lastName
        age_txt.text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener(
        {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        })
    }

    fun setData(user: List<User>)
    {
        this.userList = user
        notifyDataSetChanged()
    }
}