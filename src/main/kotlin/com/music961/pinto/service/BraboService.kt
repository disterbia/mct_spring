package com.music961.pinto.service

import com.music961.pinto.repository.BraboRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class BraboService (val braboRepo : BraboRepository,
                    val jwtService: MyJwtService){

    fun saveBrabo(request: HttpServletRequest, workId : Int) :Int {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            braboRepo.saveBrabo(usrId,workId)
            println("saveBrabo return -> 100")
            return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("saveBrabo return -> -100")
            return -100
        }
    }

    fun deleteBrabo(request: HttpServletRequest,workId : Int) : Int {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            braboRepo.deleteBrabo(usrId,workId)
            println("deleteBrabo return -> 100")
            return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("deleteBrabo return -> -200")
            return -200
        }
    }

    fun test(request : HttpServletRequest, res : Int) : Int{
        // JWT 받기
        val token = request.getHeader("TOKEN")
        val claims = jwtService.getDecodeToken(token)!!
        val id = claims.body.get("id").toString()
        try {
            braboRepo.like(id.toInt(), res.toLong())
            println("test return -> 100")
            return 100
        }catch (e : Exception){
            if(e.message!!.indexOf("PRIMARY")<0){
                //없는 리소스
                println("test return -> -100")
                return -100
            }
            braboRepo.dislike(id.toInt(), res.toLong())
            println("test return -> 200")
            return 200
        }
    }



}
