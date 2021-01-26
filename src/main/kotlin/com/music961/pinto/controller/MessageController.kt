package com.music961.pinto.controller

import com.music961.pinto.service.MessageService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/message")
@Api(tags=["MessageContorller"])
class MessageController(val service: MessageService) {

    @GetMapping("/list")
    @ApiOperation(value="쪽지함", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun list(request: HttpServletRequest): Any{
        println("message/list")
        return service.list(request)
    }

    @PostMapping("send/{target}")
    @ApiOperation(value="쪽지 보내기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러"))
    fun send(request : HttpServletRequest , @PathVariable target : String ,@RequestBody text : String) : Int {
        println("message/send/${target}")
        return service.send(request,target,text)
    }

    @PostMapping("read/{messageId}")
    @ApiOperation(value="쪽지 읽기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러"))
    fun read(@PathVariable messageId : String) : Any {
        println("message/read/${messageId}")
        return service.read(messageId)
    }
}