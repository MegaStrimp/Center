package com.example.center.fragments.date

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.center.R
import com.example.center.model.Date
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DateAdapter: RecyclerView.Adapter<DateAdapter.MyViewHolder>()
{
    private var dateList = emptyList<Date>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.date_row,parent,false))
    }

    override fun getItemCount(): Int
    {
        return dateList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val id_txt = holder.itemView.findViewById<TextView>(R.id.id_txt)
        val date_txt = holder.itemView.findViewById<TextView>(R.id.date_txt)

        val currentItem = dateList[position]

        id_txt.text = currentItem.id.toString()
        date_txt.text = currentItem.date

        /*holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener(
        {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        })*/
    }

    fun setData(date: List<Date>)
    {
        this.dateList = date
        notifyDataSetChanged()
    }
}