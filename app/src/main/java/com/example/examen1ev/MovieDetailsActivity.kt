package com.example.examen1ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen1ev.databinding.ActivityMovieDetailsBinding
import com.google.gson.Gson

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        var peli = gson.fromJson(intent.getStringExtra("movie"), Movie::class.java)


        binding.button3.setOnClickListener {
            Intent(this, MoveTitleActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.button4.setOnClickListener {
            if (binding.editTextTextPersonName.text.toString().isNotEmpty() && binding.editTextNumber.text.toString().isNotEmpty()) {
                if (binding.editTextNumber.text.toString().toInt() in 1900..2023) {
                    Intent (this, MovieDisplayActivity::class.java).also {
                        peli.director = binding.editTextTextPersonName.text.toString()
                        peli.year = binding.editTextNumber.text.toString().toInt()

                        it.putExtra("movie", gson.toJson(peli))
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "El año debe ser mayor o igual a 1900", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}