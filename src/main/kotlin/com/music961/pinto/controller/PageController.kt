package com.music961.pinto.controller

import com.music961.pinto.model.work.Page
import com.music961.pinto.model.work.Scene
import com.music961.pinto.service.BraboService
import com.music961.pinto.service.PageService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/page")
@Api(tags=["PageContorller"])
class PageController (val service: PageService){

    @GetMapping("getpages/{workId}/{epiId}/{sceneId}")
    @ApiOperation(value="page 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getPages(
            request: HttpServletRequest ,
            @PathVariable workId : String,
            @PathVariable epiId : String,
            @PathVariable sceneId : String
    ) : Any{
        println("page/getpages/${workId}/${epiId}/${sceneId}")
        return service.getPages(request,workId.toLong(),epiId.toLong(),sceneId.toLong())
    }

    @PostMapping("createpage")
    @ApiOperation(value="page 생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun createPage(
            request: HttpServletRequest ,
            @RequestBody @ApiParam("Page객체",required = true) page : Page
    ) : Any{
        println("page/createpage")
        return service.createPage(request,page)
    }


    @PostMapping("deletepage")
    @ApiOperation(value="page 삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러\n" +
            "-999 : release중인epi하위 page 삭제불가"))
    fun deletePage(
            request: HttpServletRequest ,
            @RequestBody @ApiParam("Page객체",required = true) page : Page
    ) : Int{
        println("page/deletepage")
        return service.deletePage(request,page)
    }

}
