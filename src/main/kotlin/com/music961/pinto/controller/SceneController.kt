package com.music961.pinto.controller

import com.music961.pinto.model.work.Scene
import com.music961.pinto.service.SceneService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/scene")
@Api(tags=["SceneContorller"])
class SceneController(val service : SceneService){

    @GetMapping("getmyscene/{workId}/{epiId}")
    @ApiOperation(value="나의Scene가져오기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getMyScene(
            request: HttpServletRequest,
            @PathVariable workId : String,
            @PathVariable epiId : String
    ) : Any{
        println("scene/getmyscene/${workId}/${epiId}")
        return service.getMyScene(request, workId, epiId)
    }

    @ApiOperation(value="Scene생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @PostMapping("createscene")
    fun createScene(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Scene객체",required = true) scene : Scene
    ) : Any{
        println("scene/createscene")
        return service.createScene(request, scene)
    }

    @ApiOperation(value="Scene삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Int(삭제된 항목수)\n-100 : DB에러\n" +
            "-999 : release중인epi 하위 scence 삭제불가"))
    @PostMapping("deletescene")
    fun deleteScene(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Scene객체",required = true) scene : Scene
    ) : Int{
        println("scene/deletescene")
        return service.deleteScene(request,scene)
    }

    //제목,내용(Title, Note)수정
    @ApiOperation(value="Scene수정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @PostMapping("/updatescene")
    fun updateScene(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Scene객체",required = true) scene : Scene
    ) : Any{
        println("scene/updatescene")
        return service.updateScene(request, scene)
    }

    @ApiOperation(value="Scene저장", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러"))
    @PostMapping("/savescene")
    fun saveScene(
            request : HttpServletRequest,
            @RequestBody @ApiParam("Scene객체",required = true) scene : Scene
    ) : Any{
        println("scene/savescene")
        return service.saveSence(request, scene)
    }
}