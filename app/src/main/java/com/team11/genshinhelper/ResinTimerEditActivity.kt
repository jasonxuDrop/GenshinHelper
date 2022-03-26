package com.team11.genshinhelper

import com.team11.genshinhelper.ResinTimerActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity



class ResinTimerEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resin_timer_edit)

        var resinInput = findViewById<EditText>(R.id.ResinInput)
        resinInput.setText("0")

        resinInput.addTextChangedListener(ResinEditTextWatcher())
        findViewById<Button>(R.id.Add5Button ).setOnClickListener(ResinEditButtonListener(  5, resinInput))
        findViewById<Button>(R.id.Sub5Button ).setOnClickListener(ResinEditButtonListener( -5, resinInput))
        findViewById<Button>(R.id.Add20Button).setOnClickListener(ResinEditButtonListener( 20, resinInput))
        findViewById<Button>(R.id.Sub20Button).setOnClickListener(ResinEditButtonListener(-20, resinInput))
        findViewById<Button>(R.id.Add40Button).setOnClickListener(ResinEditButtonListener( 40, resinInput))
        findViewById<Button>(R.id.Sub40Button).setOnClickListener(ResinEditButtonListener(-40, resinInput))

//        TODO: use `resinInput.setText("68")` to edit the current resin amount when page opens
//        TODO: use `resinInput.text.toString().toInt() for getting the value the user put in

        val textToDisplay2 = intent.getStringExtra("SOME_KEY2")
        resinInput?.setText(textToDisplay2)

        findViewById<Button>(R.id.SaveButton).setOnClickListener{
            saveText()
        }
    }

    fun saveText(){
        var resinInput = findViewById<EditText>(R.id.ResinInput)
        var textToPass = resinInput.text.toString()

        var intent = Intent(this, ResinTimerActivity::class.java)
        intent.putExtra("SOME_KEY", textToPass)
        startActivity(intent)
    }
    class ResinEditTextWatcher: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (p0 != null && p0.isNotEmpty()) {
                val currentAmount = p0.toString().toInt()
                if (currentAmount > 160) {
                    p0.replace(0,p0.length,"160")
                }
                else if (currentAmount < 0) {
                    p0.replace(0,p0.length,"0")
                }
            }
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    class ResinEditButtonListener (private val changeAmount : Int, var resinInput : EditText): View.OnClickListener {

        override fun onClick(p0: View?) {

            var currentAmount: Int = 0
           if (resinInput.text.isNotEmpty())
                currentAmount = resinInput.text.toString().toInt()


            currentAmount += changeAmount;

            when {
                currentAmount > 160 -> {
                    resinInput.text.replace(0,resinInput.text.length,"160")
                }
                currentAmount < 0 -> {
                    resinInput.text.replace(0,resinInput.text.length,"0")
                }
                else -> {
                    resinInput.text.replace(0,resinInput.text.length,currentAmount.toString())
                }
            }
        }
    }

    class SaveButtonListener : View.OnClickListener {
        override fun onClick(p0: View?) {


        }

    }
}