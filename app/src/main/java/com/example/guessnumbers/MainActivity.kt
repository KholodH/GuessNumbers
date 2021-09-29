package com.example.guessnumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>
    private lateinit var tvMessage:TextView
    var count=3
    var rand=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rand= Random.nextInt(11)

        clRoot = findViewById(R.id.clMain)
        messages = ArrayList()
        tvMessage=findViewById(R.id.tvMessage)

        Guess.adapter = RecyclerAdapter(this, messages)
        Guess.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.TextEnter)
        guessButton = findViewById(R.id.button)

        guessButton.setOnClickListener { addMessage() }
    }

    private fun addMessage(){
        val msg = guessField.text.toString()
        if(msg.isNotEmpty()){
            if (count>0){
                if (msg.toInt()==rand){
                    disableEntry()
                    messages.add("you guess it right!")}
                else{
                    count--
                    messages.add("your guessed Number is:$msg")
                    messages.add("your have $count guessed left")
                }
                if (count==0){
                    disableEntry()
                  messages.add("you lose :( the correct answer was $rand")
                    messages.add("Game over")
                }}

            guessField.text.clear()
            guessField.clearFocus()
            Guess.adapter?.notifyDataSetChanged()
            }
        else{
            Snackbar.make(clRoot, "Please enter some text", Snackbar.LENGTH_LONG).show()
        }
    }
    private fun disableEntry(){
        guessButton.isEnabled = false
        guessButton.isClickable = false
        guessField.isEnabled = false
        guessField.isClickable = false
    }
}