package com.example.semana6kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpUtil : ViewModel() {

    private var response = MutableLiveData("")
    val responseLive : LiveData<String> get() = response

    fun getRequest(url:String){

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val url = URL(url)
                val https = url.openConnection() as HttpsURLConnection
                https.requestMethod = "GET"
                var message = https.inputStream.bufferedReader().readText()
                withContext(Dispatchers.Main) {
                    response.value = message
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }



}