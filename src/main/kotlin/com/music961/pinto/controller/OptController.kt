package com.music961.pinto.controller

import com.music961.pinto.model.work.Opt
import com.music961.pinto.model.work.Page
import com.music961.pinto.service.BraboService
import com.music961.pinto.service.OptService
import com.music961.pinto.service.PageService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/opt")
@Api(tags=["OptContorller"])
class OptController (val service: OptService){

    @GetMapping("getopts/{workId}/{epiId}/{sceneId}/{pageId}")
    @ApiOperation(value="opt 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getOpts(
            request: HttpServletRequest,
            @PathVariable workId  : String,
            @PathVariable epiId   : String,
            @PathVariable sceneId : String,
            @PathVariable pageId  : String
    ) : Any{
        println("opt/getopts/${workId}/${epiId}/${sceneId}/${pageId}")
        return service.getOpts(request,workId.toLong(),epiId.toLong(),sceneId.toLong(),pageId.toLong())
    }

    @PostMapping("createopt")
    @ApiOperation(value="opt 생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun createOpt(
            request: HttpServletRequest,
             @RequestBody @ApiParam("Opt객체",required = true) opt : Opt
    ) : Any{
        println("opt/createopt")
        return service.createOpt(request,opt)
    }

    @PostMapping("deleteopt")
    @ApiOperation(value="opt 삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러\n" +
            "-999 : release중인epi하위 opt 삭제불가"))
    fun deletePage(
            request: HttpServletRequest ,
            @RequestBody @ApiParam("Opt객체",required = true) opt : Opt
    ) : Int{
        println("opt/deleteopt")
        return service.deleteOpt(request,opt)
    }

}
