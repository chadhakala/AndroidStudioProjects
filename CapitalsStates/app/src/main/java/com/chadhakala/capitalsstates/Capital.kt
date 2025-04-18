package com.chadhakala.capitalsstates

import android.os.Parcelable

//**********HINT**********
//There are other ways we could have
// designed this class, including as a data class.
//**********HINT**********



data class State(val state: String, val capitalCity: String) {
    override fun toString(): String {
        return "$state, $capitalCity"
    }
}


//class header, including primary constructor
class Capital(state : String, capitalCity : String) {



    //properties
    var state : String = ""
    get() = field
    set(value) {
        field = value
    }

    var capitalCity : String = ""
    get() = field
        set(value) {
            field = value
        }
            //set the property values to the corresponding
            //parameters of the primary constructor
            init {
                this.state = state
                this.capitalCity = capitalCity
            }
        }
