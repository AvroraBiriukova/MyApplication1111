package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dialogYesOrNo

/*
fun dialogYesOrNo(
    activity: Activity,
    title: String,
    message: String,
    listener: DialogInterface.OnClickListener
) {
    val builder = AlertDialog.Builder(activity)
    builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
        dialog.dismiss()
        listener.onClick(dialog, id)
    })
    builder.setNegativeButton("No", null)
    val alert = builder.create()
    alert.setTitle(title)
    alert.setMessage(message)
    alert.show()
}*/


class Card_contact: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_contact)
        title = "Карточка Контакта"
        val dbHelper = DBHelper(this)
        val Text = findViewById<TextView>(R.id.textView4)
        val TextName = findViewById<TextView>(R.id.textView2)
        val TextDate = findViewById<TextView>(R.id.textView5)
        val TextPhone = findViewById<TextView>(R.id.textView6)
        val button = findViewById<Button>(R.id.button)
        val buttonDrop = findViewById<Button>(R.id.button2)
        val buttonChange = findViewById<Button>(R.id.button3)
        val id = intent.getLongExtra("Id",0)

        val objects = dbHelper.getById(id)

        Text.text = "Фамилия:  ${objects?.title}"
        TextName.text = "Имя:      " + objects?.name
        TextDate.text = "День рождения:" + objects?.date
        TextPhone.text = "Телефон:   " + objects?.telephone

        buttonChange.setOnClickListener {

            val intent = Intent(this@Card_contact, Change_contact::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }

        buttonDrop.setOnClickListener {

            val uid = id
            dialogYesOrNo(
                this,
                "Вопрос",
                "Вы уверены,что хотите удаить это контакт?",
                DialogInterface.OnClickListener { dialog, id ->
                    dbHelper.remove(uid)
                    val intent = Intent(this@Card_contact, MainActivity::class.java)
                    startActivity(intent)
                })

        }

        button.setOnClickListener {
            val intent = Intent(this@Card_contact, MainActivity::class.java)
            startActivity(intent)
        }


    }
}