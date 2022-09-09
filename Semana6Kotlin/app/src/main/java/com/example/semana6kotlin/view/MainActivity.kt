package com.example.semana6kotlin.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.semana6kotlin.viewmodel.Counter
import com.example.semana6kotlin.viewmodel.HttpUtil
import com.example.semana6kotlin.databinding.ActivityMainBinding
import com.example.semana6kotlin.model.Request
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var counter: Counter
    private lateinit var httpUtil: HttpUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.outputTV.movementMethod = ScrollingMovementMethod()

        //instanciar viewmodel de contador
        counter = ViewModelProvider(this).get(Counter::class.java)
        counter.start()

        counter.iLive.observe(this, {
            binding.counterTV.text = "$it"
        })

        binding.goBtn.setOnClickListener(::goToSite)



    }

    fun goToSite(v : View){
        httpUtil = ViewModelProvider(this).get(HttpUtil::class.java)
        httpUtil.responseLive.observe(this,{
            binding.outputTV.text = "$it"
        })

        httpUtil.getRequest(binding.siteEditText.text.toString())
    }

    override fun onPause() {
        super.onPause()
        val request = Request(binding.siteEditText.text.toString(), binding.outputTV.text.toString())

        //Serializacion
        val json = Gson().toJson(request)
        Log.e(">>>",json)

        //Sahred preferences
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        sharedPref.edit().putString("currentState",json).apply()

    }

    override fun onResume() {
        super.onResume()

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val json = sharedPref.getString("currentState","NO_DATA")

        if(json != "NO_DATA"){
            //Deserializacion
            val objdeserie = Gson().fromJson(json,Request::class.java)
            binding.siteEditText.setText(objdeserie.url)
            binding.outputTV.text = objdeserie.response
        }
    }
}