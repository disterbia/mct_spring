package com.music961.pinto.repository

import com.music961.pinto.model.work.Cast
import com.music961.pinto.model.work.Work
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface CastRepository{
    fun createCast(cast : Cast) : Int
    fun updateCast(cast  : Cast) : Int
    fun deleteCast(cast  : Cast) : Int
    fun getCastList(workId : Long) : ArrayList<Cast>
    fun getCastListAll(list : ArrayList<Work>) : ArrayList<Cast>
}