package com.music961.pinto.repository

import com.music961.pinto.model.work.Epi
import com.music961.pinto.model.work.Work
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Mapper
interface EpiRepository{
    fun getMyEpi(workId : Long) : ArrayList<Epi>
    fun getEpiListAll(list : ArrayList<Work>) : ArrayList<Epi>
    fun getEpiList(workId : Long?) : ArrayList<Epi>
    @Transactional
    fun saveEpi (epi : Epi) : Int
    fun updateEpi(epi : Epi) : Int
    fun updateRelease(epi : Epi) : Int
    fun releasePlease(epi : Epi) : Int
    fun saveConfirm(epi :Epi)    : Int
    fun epiConfirm(epi : Epi)    : Int
    @Transactional
    fun deleteEpiCascade (epi : Epi) : Int


}