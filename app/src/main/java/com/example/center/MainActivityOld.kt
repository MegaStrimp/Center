package com.example.center

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityOld : AppCompatActivity()
{
    private lateinit var adapter: MyAdapter
    private lateinit var items: ArrayList<String>

    //region Create
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //region Find Views
        val listView = findViewById<ListView>(R.id.listView)
        val addNoteButton = findViewById<Button>(R.id.add_button)
        //endregion

        //region Initialize the Item List
        items = ArrayList()

        items.add("TEST") //TODO

        adapter = MyAdapter(this, items)
        listView.adapter = adapter
        //endregion

        //region Add Note
        val addNoteLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK)
            {
                val newNote = result.data?.getStringExtra("NEW NOTE")
                newNote?.let {
                    items.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        addNoteButton.setOnClickListener(
        {
            val intent = Intent(this@MainActivityOld, AddNoteActivity::class.java)
            addNoteLauncher.launch(intent)
        })
        //endregion
    }
    //endregion
}