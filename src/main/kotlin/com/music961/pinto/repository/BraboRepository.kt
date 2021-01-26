package com.music961.pinto.repository

import com.music961.pinto.model.Res
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface BraboRepository {
    //Res
    fun like(usr : Int, res : Long)
    fun dislike(usr : Int, res : Long)

    //Work
    fun saveBrabo(usrId : Int, workId : Int)
    fun deleteBrabo(usrId : Int ,workId : Int )

}