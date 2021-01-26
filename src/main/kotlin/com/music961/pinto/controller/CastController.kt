package com.music961.pinto.controller

import com.music961.pinto.model.work.Cast
import com.music961.pinto.service.CastService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/cast")
@Api(tags=["CastContorller"])
class CastController (val service : CastService){

    @PostMapping("createcast")
    @ApiOperation(value="cast 생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n" +
            "-300 : 상위 Work없음\n-200 : JWT인증실패\n-100 : DB에러"))
    fun createCast(
            request : HttpServletRequest,
            @RequestBody @ApiParam("cast객체",required = true) cast : Cast
    ) : Any{
        println("cast/createcast")
        return service.createCast(request, cast)
    }

    @PostMapping("updatecast")
    @ApiOperation(value="cast 수정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n" +
            "-300 : 상위 Work없음 or Res미소유\n-200 : JWT인증실패\n-100 : DB에러"))
    fun updateCast(
            request: HttpServletRequest,
            @RequestBody @ApiParam("cast객체",required = true) cast : Cast
    ) : Any{
        println("cast/updatecast")
        return service.updateCast(request, cast)
    }

    @PostMapping("deletecast")
    @ApiOperation(value="Cast 삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Int(삭제된 항목 수)\n" +
            "-300 : 상위 Work없음\n-200 : JWT인증실패\n-100 : DB에러"))
    fun deleteCast(
            request: HttpServletRequest,
            @RequestBody @ApiParam("cast객체",required = true) cast : Cast
    ) : Any{
        println("cast/deletecast")
        return service.deleteCast(request, cast)
    }

    @GetMapping("getcast/{workId}")
    @ApiOperation(value="cast 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Json\n-200 : JWT인증실패\n-100 : DB에러"))
    fun getCastList(
            request: HttpServletRequest ,
            @PathVariable workId : String
    ) : Any{
        println("cast/getcast/${workId}")
        return service.getCastList(request, workId)
    }
}
