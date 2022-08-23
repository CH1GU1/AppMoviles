package com.example.introkotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.introkotlin.databinding.ActivityAuxBinding

class AuxActivity : AppCompatActivity() {

    private val  binding : ActivityAuxBinding by lazy {
        ActivityAuxBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val generalOption = intent.extras?.getInt("option")


        when(generalOption){
            0->{
                binding.tittle.text = "PLATO FUERTE"
                binding.op1Btn.text = "Ajiaco"
                binding.op2Btn.text = "Hamburguesa"
                binding.op3Btn.text = "Baby Beef"
            }
            1->{
                binding.tittle.text = "BEBIDA"
                binding.op1Btn.text = "Cerveza"
                binding.op2Btn.text = "Limonada"
                binding.op3Btn.text = "Lulada"
            }
            2->{
                binding.tittle.text = "POSTRE"
                binding.op1Btn.text = "Tiramisú"
                binding.op2Btn.text = "Brownie"
                binding.op3Btn.visibility = View.GONE
            }
        }

        binding.op1Btn.setOnClickListener {
            val info = binding.op1Btn.text.toString()
            if (generalOption != null) {
                sendResult(generalOption,info)
            }
        }

        binding.op2Btn.setOnClickListener {
            val info = binding.op2Btn.text.toString()
            if (generalOption != null) {
                sendResult(generalOption,info)
            }
        }

        binding.op3Btn.setOnClickListener {
            val info = binding.op3Btn.text.toString()
            if (generalOption != null) {
                sendResult(generalOption,info)
            }
        }


    }

    private fun sendResult(generalOp : Int, buttonInfo : String) {

        val intent = Intent().apply {
            putExtra("generalOp",generalOp)
            putExtra("buttonInfo",buttonInfo)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}