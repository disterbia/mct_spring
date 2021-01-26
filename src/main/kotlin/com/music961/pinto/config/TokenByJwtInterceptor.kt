package com.music961.pinto.config

import com.music961.pinto.service.LogService
import com.music961.pinto.service.MyJwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenByJwtInterceptor(val logService: LogService) : HandlerInterceptor{

    @Autowired
    lateinit var myJwtService: MyJwtService

    /*===================================================================================
    * 실제 토큰 검증해서 하는 몸체
    * true  : 성공
    * false : 실패
    ===================================================================================*/

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var uri = request.requestURI
        val tokenString = request.getHeader("TOKEN")
        if(tokenString != null){
            try{
                return myJwtService.isUseableToken(tokenString,request)
            }catch (e:Exception){
                logService.logError(request,e)
                return false
            }
        }
        return false
    }
}