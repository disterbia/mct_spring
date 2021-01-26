package com.music961.pinto.service

import com.music961.pinto.model.work.Opt
import com.music961.pinto.model.work.Scene
import com.music961.pinto.repository.SceneRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@Service
class SceneService (
        val sceneRepo : SceneRepository,
        val jwtService: MyJwtService,
        val transactionService: TransactionService){

    fun getMyScene(request: HttpServletRequest, workId : String, epiId : String) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = sceneRepo.getMyScene(workId.toLong(), epiId.toLong())
            println("getMyScene return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getMyScene return -> -100")
            return -100
        }
    }

    fun createScene(request : HttpServletRequest, scene : Scene) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            scene.usrId = usrId
            scene.lastedit = Instant.now().epochSecond.toInt()
            scene.name = "새 분기"
            sceneRepo.createScene(scene)
            println("createScene return -> $scene")
            return scene
        }catch (e : Exception){
            e.printStackTrace()
            println("createScene return -> -100")
            return -100
        }
    }

    fun deleteScene(request : HttpServletRequest, scene : Scene) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        if(usrId < 1){
            println("deleteScene return -> -200")
            return -200
        }
        try {
            scene.usrId = usrId
            val result = sceneRepo.deleteScene(scene)
            if(result < 1){
                println("deleteScene return -> -999")
                return -999
            }
            println("deleteScene return -> 100")
            return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("deleteScene return -> -100")
            return -100
        }
    }

    fun updateScene(request : HttpServletRequest, scene: Scene) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            scene.usrId = usrId
            scene.lastedit = Instant.now().epochSecond.toInt()
            sceneRepo.updateScene(scene)
            println("updateScene return -> $scene")
            return scene
        }catch (e : Exception){
            e.printStackTrace()
            println("updateScene return -> -100")
            return -100
        }
    }

    fun saveSence(request : HttpServletRequest, scene: Scene) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        scene.usrId = usrId
        scene.pages.forEach {
            it.usrId = usrId
        }
        try {
            transactionService.senceTrans(scene)
            println("saveSence return -> 100")
            return 100
        }catch (e :Exception){
            println("saveSence return -> -100")
            return -100
        }
    }
}