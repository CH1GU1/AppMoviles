package com.example.introkotlin.model

import android.util.Log

class Person(id:String, name:String) {

    private var id:String = id
    var name:String = name


    init {
        println("Se ejecuta el init")

    }
    constructor() : this("NO_ID","NO_NAME"){

    }

    fun sayHello(){
        //println("Hello ${this.name}")
        Log.e(">>>", "Hello ${this.name}")

    }

    fun say(name: String){
        println(name)
    }
    companion object{
        var className:String  = "Person"

        fun sum(a:Int, b:Int):Int{
            return a+b
        }
    }
}