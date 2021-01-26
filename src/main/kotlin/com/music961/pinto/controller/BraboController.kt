package com.music961.pinto.controller

import com.music961.pinto.service.BraboService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/brabo")
@Api(tags=["BraboContorller"])
class BraboController (val service: BraboService){

    @GetMapping("/{res}")
    @ApiOperation(value="Res좋아요 올리기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n" +
            "-100 : 없는 리소스\n200 : 좋아요-1"))
    fun test(
            request : HttpServletRequest,
            @PathVariable @ApiParam("RES키",required = true)  res : Int
    ) : Int{
        println("brabo/${res}")
        return service.test(request,res)
    }

    @PostMapping("savebrabo")
    @ApiOperation(value="Work 좋아요하기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러"))
    fun saveBrabo(
            request: HttpServletRequest,
            @RequestBody workId : String
    ) : Int{
        println("brabo/savebrabo")
        return service.saveBrabo(request,workId.toInt())
    }

    @PostMapping("deletebrabo")
    @ApiOperation(value="Work 좋아요취소", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러"))
    fun deleteBrabo(
            request: HttpServletRequest ,
            @RequestBody workId : String
    ) : Int{
        println("brabo/deletebrabo")
        return service.deleteBrabo(request,workId.toInt())
    }

}
