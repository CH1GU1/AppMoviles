package com.example.introkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.example.introkotlin.databinding.ActivityMainBinding
import com.example.introkotlin.model.Person
import java.util.*

class MainActivity : AppCompatActivity() {

    private var person : Person? = null


    private val  binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val alfa : String by lazy {
        "Hola mundo"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val launcher = registerForActivityResult(StartActivityForResult(), ::onAuxResult)

        binding.mainFoodBtn.setOnClickListener{
            val intent = Intent(this, AuxActivity::class.java).apply {
                putExtra("option",0)
            }
            launcher.launch(intent)

        }

        binding.drinkBtn.setOnClickListener{
            val intent = Intent(this, AuxActivity::class.java).apply {
                putExtra("option",1)
            }
            launcher.launch(intent)

        }
        binding.dessertBtn.setOnClickListener{
            val intent = Intent(this, AuxActivity::class.java).apply {
                putExtra("option",2)
            }
            launcher.launch(intent)

        }

        binding.pedirBtn.setOnClickListener {
            val pedido : String
            val almuerzo = binding.mainFoodBtn.text.toString()
            val bebida = binding.drinkBtn.text.toString()
            val postre = binding.dessertBtn.text.toString()

            pedido = "${"Plato fuerte: "+almuerzo+" Bebida: "+bebida+" Postre: "+postre}"
            val uri = Uri.parse("http://api.whatsapp.com/send?phone=+573117182279&text={${pedido}}") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        person = Person(UUID.randomUUID().toString(),"Kevin Mera")

        person?.let { //Se DESEMPAQUETA
            it.sayHello()
        }

        person?.sayHello() //Se USA PERO NO DESEMPAQUETA

        person?.say("Alfa")
        var brand = "Microsoft"

        brand?.let {
            person?.say(it)
        }

        Log.e(">>>", Person.className)
    }

    private fun onAuxResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val data : Intent? = result.data
            val generalOption = data?.extras?.getInt("generalOp")
            val info = data?.extras?.getString("buttonInfo")

            if (generalOption != null && info != null) {
                toModify(generalOption,info)
            }
            Log.e(">>>", "${info}")
            Log.e(">>>", "${generalOption}")
        }
    }

    private fun toModify(option : Int, info : String){
        when(option){
            0->{
                binding.mainFoodBtn.text = info
            }
            1->{
                binding.drinkBtn.text = info
            }
            2->{
                binding.dessertBtn.text = info
            }
        }
    }




}


