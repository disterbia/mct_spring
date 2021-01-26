package com.music961.pinto.controller

import com.music961.pinto.model.Packet
import com.music961.pinto.service.UserService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@Api(tags=["UserContorller"])
@RequestMapping("/user")
class UserController (val userService: UserService){

    /*===================================================================================
    * 로그인 및 회원가입
    ===================================================================================*/

    @PostMapping("/login")
    @ApiOperation(value="로그인 및 회원가입")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Packet"))
    fun login(@RequestBody @ApiParam("구글토큰",required = true) gToken : String? , request : HttpServletRequest): Packet {
        println("user/login")
        return userService.login(gToken, request)
    }

    @PostMapping("/refresh")
    @ApiOperation(value="JWT토큰 갱신")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON"))
    fun refresh(request : HttpServletRequest): Any {
        println("user/refresh")
        return userService.refresh(request)
    }

    @PostMapping("/saveprofile")
    @ApiOperation(value = "프로필사진 변경")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-300 : 카테고리값 잘못됨\n-100 : 파일업로드실패"))
    fun saveFile(request: HttpServletRequest,@RequestBody fileName : String) : Int{
        println("user/saveprofile")
        return userService.upload(request,fileName)
    }

    @PostMapping("/deleteprofile")
    @ApiOperation(value = "프로필사진 삭제")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-300 : 카테고리값 잘못됨\n-100 : 파일업로드실패" +
            "\n-404 : 파일없음"))
    fun deleteFile(request: HttpServletRequest) : Int{
        println("user/deleteprofile")
        return userService.delete(request)
    }
}