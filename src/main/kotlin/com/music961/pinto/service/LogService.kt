package com.music961.pinto.service


import org.apache.juli.logging.LogFactory
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import kotlin.Exception

@Service
class LogService {
    val logger = LogFactory.getLog(LogService::class.java)

    fun logError(request: HttpServletRequest,exception: Exception){
        var ip: String? = request.getHeader("X-Forwarded-For")
        if (ip == null) {
            ip = request.getRemoteAddr()
        }
        logger.error("USER_IP : $ip ERROR:$exception")
    }

    fun logError(request: HttpServletRequest,msg : String){
        var ip: String? = request.getHeader("X-Forwarded-For")
        if(ip == null){
            ip = request.getRemoteAddr()
        }
        logger.error("USER_IP : $ip ERROR:$msg")
    }

    fun logIp(request: HttpServletRequest,id : Int): String? {

        var ip: String? = request.getHeader("X-Forwarded-For")
        if (ip == null) {
            ip = request.getRemoteAddr()
        }
        logger.info("LOGIN IP : $ip USER_KEY: $id")
        println("logIp return -> $ip")
        return ip
    }
}