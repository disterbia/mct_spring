package com.music961.pinto.service

import com.music961.pinto.model.work.Opt
import com.music961.pinto.model.work.Page
import com.music961.pinto.repository.OptRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class OptService (
        val optRepo : OptRepository,
        val jwtService: MyJwtService
){
    fun getOpts(
            request: HttpServletRequest,
            workId  : Long,
            epiId   : Long,
            sceneId : Long,
            pageId  : Long
    ) :Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = optRepo.getOpts(workId,epiId,sceneId,pageId)
            println("getOpts return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getOpts return -> -100")
            return -100
        }
    }

    fun createOpt(request: HttpServletRequest , opt: Opt) : Any {
        val usrId = jwtService.getUsrIdByJWT(request)
        try{
            opt.usrId = usrId
            optRepo.createOpt(opt)
            println("createOpt return -> $opt")
            return opt
        }catch (e :Exception){
            e.printStackTrace()
            println("createOpt return -> -100")
            return -100
        }
    }

    fun deleteOpt(request : HttpServletRequest, opt : Opt) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = optRepo.deleteOpt(opt)
            if(result < 1){
                println("deleteOpt return -> -999")
                return -999
            }
            println("deleteOpt return -> 100")
             return 100
        }catch (e : Exception){
            println("deleteOpt return -> -100")
            return -100
        }
    }
}