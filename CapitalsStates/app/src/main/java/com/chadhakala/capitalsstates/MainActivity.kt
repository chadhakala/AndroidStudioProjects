package com.chadhakala.capitalsstates

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chadhakala.capitalsstates.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var stateList: ArrayList<Capital>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //testing
        val rawDataArray = resources.getStringArray(R.array.states)
        var stateName: String
        var capitolName: String
        var stringArray: List<String>
        var capital: Capital
        stateList = ArrayList<Capital>()

        //testing

        for (state in rawDataArray) {
            Log.i("state", state.toString())
        }

        for (state in rawDataArray) {
            stringArray = state.split(",")
            stateName = stringArray[0]
            capitolName = stringArray[1]

            //create instance of Capital *class*
            //--> NOTE <-- var capital : Capital
            capital = Capital(stateName, capitolName)
            stateList.add(capital)

            /*       capital = Capital(stateName, capitolName)
            stateList.add(capital)*/
        }//end for loop

        var capitalObject = stateList.get(Random.nextInt(stateList.size))
        var messageString = "${capitalObject.capitalCity} " +
                "is the capital of ${capitalObject.state}"
        binding.capitalInfo.setText(messageString)
    }


}//end setOnCLickListener"



//let's try the forEach style and print out the data
//"it" represents an individual object, as the array list
//iterates through its elements using the forEach function


      //  binding.nextButton.setOnClickListener {
       //     capitalObject = stateList.get(Random.nextInt(stateList.size))
         //   messageString = "${capitalObject.capitalCity} " +
           //         "is the capital of ${capitalObject.state}"
            //binding.capitalInfo.setText(messageString)




        //**********HINT**********
//You could also look into doing this with the
//functional style forEach function.
        //**********HINT**********

