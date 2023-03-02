package com.example.examen1ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen1ev.databinding.ActivityMoveTitleBinding
import com.google.gson.Gson

class MoveTitleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMoveTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var peli = Movie()

        binding.button3.setOnClickListener {
            if (binding.editTextTextPersonName.text.toString().isNotEmpty() && binding.editTextNumber.text.toString().isNotEmpty()) {
                Intent (this, MovieDetailsActivity::class.java).also {
                    peli.title = binding.editTextTextPersonName.text.toString()
                    peli.duration = binding.editTextNumber.text.toString().toInt()

                    val gson = Gson()
                    it.putExtra("movie", gson.toJson(peli))
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}