package com.music961.pinto.service

import com.music961.pinto.model.Message
import com.music961.pinto.repository.MessageRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp
import javax.servlet.http.HttpServletRequest

@Service
class MessageService(
        val messageRepo:MessageRepository,
        val jwtService: MyJwtService){

    fun list(request: HttpServletRequest) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try{
            val result = messageRepo.list(usrId)
            println("list return -> $result")
            return result
        }catch (e:Exception){
            e.printStackTrace()
            println("list return -> -100")
            return -100
        }
    }

    fun send(request : HttpServletRequest , target : String , text : String) : Int {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            messageRepo.send(usrId,target.toInt(),text)
            println("send return -> 100")
            return 100
        }catch (e:Exception){
            e.printStackTrace()
            println("send return -> -100")
            return -100
        }
    }

    fun read(messageId : String) : Any {
        try {
            messageRepo.read(messageId.toInt())
            println("return -> 100")
            return 100
        }catch (e:Exception){
            e.printStackTrace()
            println("return -> -100")
            return -100
        }
    }
}