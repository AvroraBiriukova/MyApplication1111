package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DBHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        var allData = dbHelper.getAll()/*.filter { it.title.contains("Od") }*/
        list.addAll(allData)



        editText.doAfterTextChanged { it ->

            allData = dbHelper.getAll().filter { item -> item.title.contains(it.toString(), true) }
            list.clear()
            list.addAll(allData)
            adapter.notifyDataSetChanged()
        }





        adapter = RecyclerAdapter(list) {
            val id = list[it].id
            val intent = Intent(this@MainActivity, Card_contact::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, Addendum_contact::class.java)
            startActivity(intent)

        }

    }
}


