package com.music961.pinto.repository

import com.music961.pinto.model.Res
import com.music961.pinto.model.ResUsr
import com.music961.pinto.model.work.Cast
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Mapper
interface ResRepository {
    fun getFileList(id : Int, cFileList : ArrayList<Int>) : ArrayList<Int>
    fun getResource(id : Int, filename : Int) : Res?
    fun getMusic(filename : Int) : Res?
    fun getGemPrice(id : Int, res : Int) : HashMap<String, Int>
    fun getMyRes(cast : Cast)      : Int
    fun getAllListOrderByBrabo0(cat : Int, usr : Int) :  ArrayList<Res>       //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getAllListOrderByBrabo1(cat : Int, usr : Int) :  ArrayList<Res>       //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getAllListOrderByBrabo2(cat : Int, usr : Int) :  ArrayList<Res>       //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByBrabo0(cat : Int, usr : Int,fn : Int) : ArrayList<Res> //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByBrabo1(cat : Int, usr : Int,fn : Int) : ArrayList<Res> //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByBrabo2(cat : Int, usr : Int,fn : Int) : ArrayList<Res> //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getAllListOrderByTime0(cat : Int, usr : Int) : ArrayList<Res>         //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getAllListOrderByTime1(cat : Int, usr : Int) : ArrayList<Res>         //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getAllListOrderByTime2(cat : Int, usr : Int) : ArrayList<Res>         //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByTime0(cat : Int, usr : Int, fn : Int) : ArrayList<Res>  //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByTime1(cat : Int, usr : Int, fn : Int) : ArrayList<Res>  //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getListOrderByTime2(cat : Int, usr : Int, fn : Int) : ArrayList<Res>  //마켓에서 쓰일것. 좋아요는 조인해서 들고옴.
    fun getResByResGroup(cat : Int, id : Int,group : Long) : ArrayList<Res>
    fun searchResByResGroup(id : Int,group : Long) : ArrayList<Res>
    fun getResUsrByResGroup(id : Int,group : Long) : ArrayList<Res>
    fun getLikeResByResGroup(id : Int,group : Long) : ArrayList<Res>
    fun getMyLikeList(id: Int) : ArrayList<Res>
    fun resSearch(id:Int ,text : String) : ArrayList<Res>
    fun buyList(id : Int) : ArrayList<Res>
    fun soldList(id : Int) : ArrayList<Res>

    fun saveFile(res : Res)
    @Transactional
    fun buyStep1(id : Int, res : Long)
    @Transactional
    fun buyStep2(id : Int, price : Int)
    fun getList(cat : Int) : ArrayList<Res>
    fun saveBrabo(usrId : Int ,resId : Int)
    fun deleteBrabo(usrId : Int , resId : Int)
}