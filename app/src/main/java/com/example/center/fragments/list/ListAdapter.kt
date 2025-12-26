package com.example.center.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.center.R
import com.example.center.model.Date
import com.example.center.model.Task

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>()
{
    private var taskList = emptyList<Task>()
    private var currentDate = Date(0, "")


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int
    {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val id_txt = holder.itemView.findViewById<TextView>(R.id.id_txt)
        val text_txt = holder.itemView.findViewById<TextView>(R.id.text_txt)
        val category_txt = holder.itemView.findViewById<TextView>(R.id.category_txt)

        val currentItem = taskList[position]

        val border = when (currentItem.category)
        {
            "Work" -> R.drawable.rotating_border_work
            "Media" -> R.drawable.rotating_border_media
            "Life" -> R.drawable.rotating_border_life
            else -> R.drawable.rotating_border_default
        }

        holder.itemView.findViewById<View>(R.id.border_container)
            .setBackgroundResource(border)

        id_txt.text = currentItem.id.toString()
        text_txt.text = currentItem.text
        category_txt.text = currentItem.category

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener(
            {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(
                    currentItem,
                    currentDate
                )
                holder.itemView.findNavController().navigate(action)
            })
    }

    fun setData(task: List<Task>, date: Date)
    {
        this.taskList = task
        this.currentDate = date
        notifyDataSetChanged()
    }
}