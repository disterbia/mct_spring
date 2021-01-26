package com.music961.pinto.controller

import com.music961.pinto.model.work.Epi
import com.music961.pinto.service.EpiService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/epi")
@Api(tags=["EpiContorller"])
class EpiController(val service : EpiService){

    @ApiOperation(value="epi조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @GetMapping("getmyepi/{workId}")
    fun getMyEpi(
            request: HttpServletRequest,
            @PathVariable workId : String
    ) : Any{
        println("epi/getmyepi/${workId}")
        return service.getMyEpi(request, workId)
    }

    @ApiOperation(value="epi생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @PostMapping("createepi")
    fun createEpi(
            request : HttpServletRequest,
            @RequestBody workId : String
    ) : Any{
        println("epi/createepi")
        return service.createEpi(request, workId)
    }

    @PostMapping("createepiwaterfall")
    @ApiOperation(value="Epi,Scene,Page 동시 생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun createEpiWaterFall(request: HttpServletRequest,
                           @RequestBody epi : Epi) : Any{
        println("epi/createepiwaterfall")
        return service.createEpiWaterFall(request,epi)
    }

    @ApiOperation(value="epi삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Int(삭제된목 항 수)\n-100 : DB에러\n" +
            "-999 : release중인epi 삭제불가"))
    @PostMapping("deleteepi")
    fun deleteEpi(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Epi객체",required = true) epi : Epi
    ) : Int{
        println("epi/deleteepi")
        return service.deleteEpi(request, epi)
    }

    @PostMapping("updateepi")
    @ApiOperation(value="epi수정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 0 or 1\n-100 : DB에러"))
    fun updateEpi(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Epi객체",required = true) epi : Epi
    ) : Any{
        println("epi/updateepi")
        return service.updateEpi(request,epi)
    }

    @PostMapping("updaterelease")
    @ApiOperation(value="Epi공개/비공개 설정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 0 or 1\n-100 : DB에러"))
    fun updateRelease(request: HttpServletRequest,
                      @RequestBody @ApiParam("epi객체",required = true) epi : Epi) : Int{
        println("epi/updaterelease")
        return service.updateRelease(request,epi)
    }

    @PostMapping("releaseplease")
    @ApiOperation(value="Epi심사요청", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 0 or 1 or 2\n-100 : DB에러\n" +
            "0:심사전 비공개 1 :심사중 2:심사완료 공개 3:심사완료 비공개"))
    fun releasePlease(request: HttpServletRequest,
                      @RequestBody @ApiParam("epi객체",required = true) epi : Epi) : Int{
        println("epi/releaseplease")
        return service.releasePlease(request,epi)
    }

    @PostMapping("epiconfirm")
    @ApiOperation(value="관리자 심사", notes="==보류중 작동안됨==\nRequest Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 0 or 1\n-100 : DB에러"))
    fun epiConfirm(request: HttpServletRequest,
                      @RequestBody @ApiParam("epi객체",required = true) epi : Epi) : Int{
        println("epi/updaterelease")
        return service.epiConfirm(request,epi)
    }

    @PostMapping("saveepi")
    @ApiOperation(value="epi저장", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun saveEpi(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Epi객체",required = true) epi : Epi
    ) : Any{
        println("epi/saveepi")
        return service.saveEpi(request, epi)
    }


}