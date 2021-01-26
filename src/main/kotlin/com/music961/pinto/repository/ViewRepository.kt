package com.music961.pinto.repository

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface ViewRepository{
    fun saveViewInfo(usrId :Int , id : Int)
}