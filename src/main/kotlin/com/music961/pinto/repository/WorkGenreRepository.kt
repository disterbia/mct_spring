package com.music961.pinto.repository

import com.music961.pinto.model.work.Genre
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface WorkGenreRepository {

    fun saveWorkGenre (genre : Genre) : Long
    fun deleteWorkGenreByWorkId (workId : Long)
}