package com.example.findmyage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    calculateBtn.setOnClickListener {
        try {
            if(dobEditText.text.isEmpty())throw java.lang.NullPointerException()
            val yob: Int = Integer.parseInt(dobEditText.text.toString())
            if(yob<0)throw NumberFormatException()
            val currentYear:Int=Calendar.getInstance().get(Calendar.YEAR)
            val userAge:Int=currentYear-yob
            when {
                userAge<0 -> ageTextView.text="You haven't born yet!"
                userAge<=1 -> ageTextView.text="$userAge year"
                else -> ageTextView.text="$userAge years"
            }
        }catch(nfe:NumberFormatException){
            dobEditText.error = "Invalid Input!"
        }
        catch (npe:NullPointerException){
            dobEditText.error="This field cannot be empty!"
        }catch (e:Exception){
            dobEditText.error="Some error occurred!"
        }

    }

    }
}