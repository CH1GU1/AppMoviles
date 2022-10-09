package com.example.authclase.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authclase.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _authState = MutableLiveData(AuthState(AuthResult.IDLE, "Starting..."))

    val authState: LiveData<AuthState> get() = _authState

    //Accion de registro
    fun signUp(name:String, phone:String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
                //Registrar el objeto en firestore
                val user = User(Firebase.auth.currentUser!!.uid,name,phone,email)
                Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).set(user).await()

                withContext(Dispatchers.Main) {
                    _authState.value = AuthState(AuthResult.SUCCESS, "Success")
                }

            } catch (ex: Exception) {
                Log.e(">>>", ex.localizedMessage)
                withContext(Dispatchers.Main) {
                    _authState.value = AuthState(AuthResult.FAIL, ex.localizedMessage)
                }
            }
        }
    }
}

data class AuthState(val result: AuthResult, val message: String)
enum class AuthResult { IDLE, FAIL, SUCCESS }

/*
enum class AuthResult(val state:String){
    IDLE( "No comenzado"),
    FAIL("No exitoso"),
    SUCCESS("Éxito")
*/