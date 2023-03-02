package com.example.examen1ev

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen1ev.databinding.ActivityMovieDisplayBinding
import com.google.gson.Gson

class MovieDisplayActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        var peli = gson.fromJson(intent.getStringExtra("movie"), Movie::class.java)

        var sharedpref = getSharedPreferences("peliculas", MODE_PRIVATE)
        val peliculas = sharedpref.getString("peliculas", "")
        if (peliculas != "") {
            val pelis = gson.fromJson(peliculas, Movies::class.java)
            pelis.movies.add(peli)
            sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
        } else {
            val pelis = Movies()
            pelis.movies.add(peli)
            sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
        }

        binding.textView5.text = peli.title
        binding.textView6.text = peli.duration.toString()
        binding.textView7.text = peli.director
        binding.textView8.text = peli.year.toString()

        binding.button.setOnClickListener {
            Intent(this, MoveTitleActivity::class.java).also {
                it.putExtra("movie", gson.toJson(peli))
                startActivity(it)
            }
        }

        binding.button2.setOnClickListener {
            Intent(this, MovieDetailsActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.button5.setOnClickListener {
            if (binding.editTextTextPersonName2.text.toString().isNotEmpty()) {
                var sharedpref = getSharedPreferences("peliculas", MODE_WORLD_WRITEABLE)
                val peliculas = sharedpref.getString("peliculas", "")
                var pelis = Movies()
                if (peliculas != "") {
                    pelis = gson.fromJson(peliculas, Movies::class.java)
                    pelis.movies.add(peli)
                    sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
                } else {
                    pelis = Movies()
                    pelis.movies.add(peli)
                    sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
                }
                var found = false
                pelis.movies.forEach { movie ->
                    if (movie.title == binding.editTextTextPersonName2.text.toString()) {
                        found = true
                        peli = movie
                    }
                }

                if (found) {
                    binding.textView5.text = peli.title
                    binding.textView6.text = peli.duration.toString()
                    binding.textView7.text = peli.director
                    binding.textView8.text = peli.year.toString()
                } else {
                    Toast.makeText(this, "No se ha encontrado la película", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button6.setOnClickListener {
            if (binding.editTextTextPersonName2.text.toString().isNotEmpty()) {
                var sharedpref = getSharedPreferences("peliculas", MODE_WORLD_WRITEABLE)
                val peliculas = sharedpref.getString("peliculas", "")
                var pelis = Movies()
                if (peliculas != "") {
                    pelis = gson.fromJson(peliculas, Movies::class.java)
                    pelis.movies.add(peli)
                    sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
                } else {
                    pelis = Movies()
                    pelis.movies.add(peli)
                    sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
                }
                var found = false
                pelis.movies.forEach { movie ->
                    if (movie.title == binding.editTextTextPersonName2.text.toString()) {
                        found = true
                        peli = movie
                    }
                }

                if (found) {
                    if (peli.title == binding.textView5.text.toString()) {
                        binding.textView5.text = ""
                        binding.textView6.text = ""
                        binding.textView7.text = ""
                        binding.textView8.text = ""
                    }
                    pelis.movies.remove(peli)
                    sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
                } else {
                    Toast.makeText(this, "No se ha encontrado la película", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button7.setOnClickListener {
            peli.isFavorite = true
            val pelis = gson.fromJson(peliculas, Movies::class.java)
            pelis.favMovies.add(peli)
            sharedpref.edit().putString("peliculas", gson.toJson(pelis)).apply()
        }

        binding.button8.setOnClickListener {
            Intent(this, FavouriteMoviesActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}