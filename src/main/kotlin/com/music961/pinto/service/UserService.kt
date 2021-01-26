package com.music961.pinto.service

import com.google.firebase.auth.FirebaseAuth
import com.music961.pinto.model.Packet
import com.music961.pinto.repository.UserRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class UserService (val userRepo : UserRepository,
                   val jwtService: MyJwtService,
                   val logService: LogService,
                   val fileService: FileService) {

    fun login(gToken : String?, request : HttpServletRequest): Packet {
        if(gToken == null || gToken == ""){
            logService.logError(request,"-404")
            println("login return -> ${Packet(-404,"faild",null)}")
            return Packet(-404,"faild",null)
        }
        try {
            val userInfo = FirebaseAuth.getInstance().verifyIdToken(gToken)
            //Token이 유효한지 검증
            if(userInfo.email != null){
                var user = userRepo.loginGoogleOauth(userInfo.email)
                if(user != null){
                    //회원
                    val jwtToken = jwtService.makeToken(user)
                    println(jwtToken)
                    val code = jwtService.getUsrIdByJWTString(jwtToken)
                    if(code > 0){
                        logService.logIp(request,code)
                    }else{
                        logService.logError(request,code.toString())
                    }
                    println("login return -> ${Packet(100, jwtToken, user)}")
                    return Packet(100, jwtToken, user)
                }else{
                    //비회원
                    try {
                        userRepo.joinByGoogle(userInfo.name, userInfo.email)
                        user = userRepo.loginGoogleOauth(userInfo.email)
                        val jwtToken = jwtService.makeToken(user)
                        val code = jwtService.getUsrIdByJWTString(jwtToken)
                        if(code > 0){
                            logService.logIp(request,code)
                        }else{
                            logService.logError(request,code.toString())
                        }
                        println("login return -> ${Packet(100, jwtToken, user)}")
                        return Packet(100, jwtToken, user)
                    }catch (e : Exception){
                        logService.logError(request,e)
                        println("login return -> ${Packet(200, "", null)}")
                        return Packet(200, "", null) // insert 실패
                    }
                }
            }else {
                logService.logError(request,"200")
                println("login return -> ${Packet(200, "", null)}")
                return Packet(200, "", null) //실패
            }

        }catch (e: Exception){
            e.printStackTrace()
            logService.logError(request,e)
            println("login return -> ${Packet(200, "", null)}")
            return Packet(200, "", null) //실패
        }
    }

    fun refresh(request: HttpServletRequest) : Any{
        val jwtToken = request.getHeader("TOKEN")
        val errorResult = hashMapOf<String,Any>()
            try {
                val claims = jwtService.getDecodeToken(jwtToken)!!
                val email  = claims.body["email"].toString()
                val user = userRepo.loginGoogleOauth(email)
                val refreshToken = jwtService.makeToken(user)
                val result = hashMapOf<String,Any>()
                result.put("result",200)
                result.put("token",refreshToken)
                result.put("user",user)
                println("refresh return -> $result")
                return result
            }catch (e: Exception){
                e.printStackTrace()
                errorResult.put("result",-200)
                errorResult.put("token","")
                println("refresh return -> $errorResult")
                return errorResult
            }
    }

    fun upload(request: HttpServletRequest,fileName:  String) : Int{
        val usrId =jwtService.getUsrIdByJWT(request)
        try {
            return fileService.saveFileFromBase64String(usrId.toString(), fileName,1) // cat - 0:res 1:profile
        }catch (e:Exception){
            e.printStackTrace()
            println("upload return -> -100")
            return -100
        }
    }

    fun delete(request: HttpServletRequest) : Int{
        val usrId =jwtService.getUsrIdByJWT(request)
        try {
            return fileService.deleteFile(usrId.toString(),1) // cat - 0:res 1:profile
        }catch (e:Exception){
            e.printStackTrace()
            println("delete return -> -100")
            return -100
        }
    }
}