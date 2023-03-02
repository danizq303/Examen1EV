package com.example.examen1ev

class Movie {
    var title : String = ""
    var duration : Int = 0
    var director : String = ""
    var year : Int = 0
    var isFavorite : Boolean = false

    constructor(title : String, duration : Int, director : String, year : Int) {
        this.title = title
        this.duration = duration
        this.director = director
        this.year = year
    }

    constructor()
}