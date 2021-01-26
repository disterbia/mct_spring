package com.music961.pinto.service

import com.music961.pinto.repository.GenreRepository
import org.springframework.stereotype.Service

@Service
class GenreService (val genreRepo : GenreRepository){

    fun autoCompleteGenre(genreValue : String) : Any{
        try {
            val result =genreRepo.autoCompleteGenre(genreValue)
            println("autoCompleteGenre return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("autoCompleteGenre return -> -100")
            return -100
        }
    }
}
