package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        val btn = findViewById<Button>(R.id.button)
        val inputword = findViewById<EditText>(R.id.InputWord)
        val guessword1 = findViewById<TextView>(R.id.word1)
        val checkword1 = findViewById<TextView>(R.id.wordcheck1)
        val guessword2 = findViewById<TextView>(R.id.word2)
        val checkword2 = findViewById<TextView>(R.id.wordcheck2)
        val guessword3 = findViewById<TextView>(R.id.word3)
        val checkword3 = findViewById<TextView>(R.id.wordcheck3)
        val answer = findViewById<TextView>(R.id.answer)

        btn.setOnClickListener {
            count++;
            if(count == 4){
                Toast.makeText(it.context, "More than 3 guess!", Toast.LENGTH_SHORT).show()
                answer.text = wordToGuess
                answer.visibility = View.VISIBLE
                btn.text = "RESTART"

                btn.setOnClickListener{
                    count = 0;
                    answer.visibility = View.INVISIBLE
                    guessword1.text = ""
                    checkword1.text = ""
                    guessword2.text = ""
                    checkword2.text = ""
                    guessword3.text = ""
                    checkword3.text = ""
                    btn.text = "GUESS!"
                }
            }
            else{
                if(count % 3 == 1){
                    guessword1.text = inputword.text;
                    val check1 = checkGuess(guessword1.text.toString())
                    checkword1.text = check1
                }
                else if(count % 3 == 2){
                    guessword2.text = inputword.text;
                    val check2 = checkGuess(guessword2.text.toString())
                    checkword2.text = check2
                }
                else{
                    guessword3.text = inputword.text;
                    val check3 = checkGuess(guessword3.text.toString())
                    checkword3.text = check3
                }
            }
        }
    }
}