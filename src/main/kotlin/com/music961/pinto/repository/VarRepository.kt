package com.music961.pinto.repository

import com.music961.pinto.model.work.Var
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface VarRepository{
    fun saveVar(v : Var) : Long
    fun deleteVarByWorkId (workId : Long)

}