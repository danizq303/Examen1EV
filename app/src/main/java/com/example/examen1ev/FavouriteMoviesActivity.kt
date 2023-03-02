package com.example.examen1ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen1ev.databinding.ActivityFavouriteMoviesBinding
import com.google.gson.Gson

class FavouriteMoviesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavouriteMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()

        val sharedpref = getSharedPreferences("peliculas", MODE_WORLD_WRITEABLE)
        val peliculas = sharedpref.getString("peliculas", "")
        if (peliculas != "") {
            val pelis = gson.fromJson(peliculas, Movies::class.java)
            if (pelis.favMovies.size == 0) {
                Toast.makeText(this, "No hay peliculas favoritas", Toast.LENGTH_SHORT).show()
            } else {
                pelis.favMovies.forEach {
                    binding.textView9.text = binding.textView9.text.toString() + it.title + "\n"
                }
            }
        }



        binding.button9.setOnClickListener {
            onBackPressed()
        }
    }
}