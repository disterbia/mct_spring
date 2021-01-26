package com.music961.pinto.repository

import com.music961.pinto.model.work.Genre
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface GenreRepository{

    fun getGenre(workId: Long) : ArrayList<Genre>
    fun autoCompleteGenre(genreValue : String) : ArrayList<String>
    fun saveGenre(genre : Genre) : Long
}