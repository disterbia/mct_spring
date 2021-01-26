package com.music961.pinto.repository

import com.music961.pinto.model.work.*
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface WorkRepository {

    fun getMyWork(usrId : Int) : ArrayList<Work>
    fun getWorkByRelease(usrId : Int ,id : Long) : Work?
    fun getWorkByReleaseObBrabo() : Work?
    fun getWorkByReleaseObTime() : Work?
    fun getWorkBrabo(usrId : Int)  : ArrayList<Work>
    fun getAllWork() : ArrayList<Work>
    fun getWorkInfo(usrId : Int,workId : Long) : ArrayList<Work> //장르가 여러개일때 1개이상의 Row가 떨어져서 컬렉션으로 받음
    fun getWorkPopular(usrId : Int) : HashMap<String,Int>
    fun saveBookMark(usrId :Int ,workId : Long , epiId : Long ,sceneId : Long ,pageId : Long) : Int
    fun saveWork(work : Work) : Long
    fun updateWork(work : Work)
    fun theEnd(usrId : Int , workId : Long) : Int

    fun deleteWorkCascade (workId : Long, usrId : Int) : Int






















}