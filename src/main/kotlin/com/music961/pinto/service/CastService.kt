package com.music961.pinto.service

import com.music961.pinto.model.work.Cast
import com.music961.pinto.repository.CastRepository
import com.music961.pinto.repository.ResRepository
import com.music961.pinto.repository.WorkRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class CastService(
        val castRepo   : CastRepository,
        val jwtService : MyJwtService,
        val workRepo   : WorkRepository,
        val resRepo    : ResRepository){

    fun createCast(request: HttpServletRequest, cast : Cast) : Any{
        val usrId  = jwtService.getUsrIdByJWT(request)
        try {
            val workList = workRepo.getMyWork(usrId)
            if(workList.size < 1 ){
                return -300
            }
            cast.usrId  = usrId
            cast.resNo  = -100
            cast.zoom   = 1.00
            cast.biasX  = 0.5
            cast.biasY  = 0.5

            castRepo.createCast(cast)
            println("createCast return -> $cast")
            return cast
        }catch (e : Exception){
            e.printStackTrace()
            println("createCast return -> -100")
            return -100
        }

    }

    fun updateCast(request: HttpServletRequest, cast: Cast) :Any {
        val usrId  = jwtService.getUsrIdByJWT(request)
        try {
            val workList = workRepo.getMyWork(usrId)
            if (workList.size < 1) {
                println("updateCast return -> -300")
                return -300
            }
            cast.usrId = usrId
            if(resRepo.getMyRes(cast) < 1){
                println("updateCast return -> -300")
                return -300
            }
            castRepo.updateCast(cast)
            println("updateCast return -> $cast")
            return cast
        }catch (e : Exception){
            e.printStackTrace()
            println("updateCast return -> -100")
            return -100
        }
    }

    fun deleteCast(request: HttpServletRequest, cast : Cast) : Int{
        val usrId  = jwtService.getUsrIdByJWT(request)
        try {
            val workList = workRepo.getMyWork(usrId)
            if(workList.size < 1 ){
                println("deleteCast return -> -300")
                return -300
            }
            cast.usrId = usrId
            return castRepo.deleteCast(cast)
        }catch (e : Exception){
            e.printStackTrace()
            println("deleteCast return -> -100")
            return -100
        }
    }

    fun getCastList(request: HttpServletRequest, workId : String) : Any {
        val usrId  = jwtService.getUsrIdByJWT(request)
        try {
            val result =castRepo.getCastList(workId.toLong())
            println("getCastList return -> $result")
            return result
        }catch (e : Exception) {
            e.printStackTrace()
            println("getCastList return -> -100")
            return -100
        }
    }
}