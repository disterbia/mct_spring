package com.music961.pinto.repository


import com.music961.pinto.model.work.Opt
import com.music961.pinto.model.work.Work
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Mapper
interface OptRepository {

    fun getOptList(workId : Long?) : ArrayList<Opt>
    fun getOptListAll(list : ArrayList<Work>) : ArrayList<Opt>
    fun getOpts(workId : Long , epiId : Long , sceneId: Long , pageId : Long) : ArrayList<Opt>

    fun createOpt(opt : Opt) : Int
    @Transactional
    fun saveOpts(opts : ArrayList<Opt>) : Int
    fun deleteOpt(opt : Opt) : Int
}