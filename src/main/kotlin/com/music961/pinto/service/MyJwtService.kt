package com.music961.pinto.service

import com.music961.pinto.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest


@Service
class MyJwtService() {
    @Autowired
    lateinit var logService: LogService
    /*===================================================================================
    * 토큰 유효시간 수정해서 씁시다
    ===================================================================================*/

    private val SECRET_KEY = "TEST"
    private val TOKEN_VALID_TIME = 72 * 60 * 60 * 1000L // 3일

    // JWT 검증
    fun isUseableToken(token : String, request: HttpServletRequest) : Boolean {
        try {
            //토큰이 유효하지 않을 때 파싱에서 에러가 남
            val claimsJws : Jws<Claims> = Jwts.parser()
                                              .setSigningKey(SECRET_KEY)
                                              .parseClaimsJws(token)
            return !claimsJws.body.expiration.before(Date())
        }catch(e : Exception) {
            logService.logError(request,e)
            return false
        }
    }

    // JWT 생성
    fun makeToken(user : User) : String {
        println(TOKEN_VALID_TIME)
        val now = Date()
        val expDate = Date(now.time + TOKEN_VALID_TIME)
        println(now)
        println(expDate)

        return Jwts.builder()
                .setHeaderParam("tpy", "JWT")
                .claim("id", user.id)
                .claim("email", user.email)
                .setIssuedAt(now) //생성시간
                .setExpiration(expDate) //유효기간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact()
    }

    // JWT 복호화
    fun getDecodeToken(token : String) : Jws<Claims>? {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
    }

    // JWT 유저 id확인
    fun getUsrIdByJWT(request: HttpServletRequest) : Int{
        var id = "0"
        val token = request.getHeader("TOKEN")
        val claims = getDecodeToken(token)!!
        id = claims.body.get("id").toString()
        return id.toInt()
    }

    // JWT 유저KEY LOG
    fun getUsrIdByJWTString(jwtString : String) : Int{
        var id = "0"
        val claims = getDecodeToken(jwtString)!!
        id = claims.body.get("id").toString()
        return id.toInt()
    }
}