package com.music961.pinto.controller


import com.music961.pinto.model.work.*
import com.music961.pinto.service.WorkService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/work")
@Api(tags=["WorkController"])
class WorkController (val service: WorkService){

    @PostMapping("deletework")
    @ApiOperation(value="work삭제", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러\n" +
            "-999 : release중인epi포함 work 삭제불가"))
    fun deleteWork(
            request: HttpServletRequest,
            @RequestBody @ApiParam("work객체",required = true) work: Work
    ) : Int{
        println("work/deletework")
        return service.deleteWork(request, work)
    }

    @ApiOperation(value="나의work조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n-1 : 삭제불가"))
    @GetMapping("getmywork")
    fun getMyWork(request: HttpServletRequest) : Any{
        println("work/getmywork")
        return service.getMyWork(request)
    }

    @ApiOperation(value="최신순 work 20개조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @GetMapping("worklist")
    fun getAllWork(request: HttpServletRequest) : Any{
        println("work/worklist")
        return service.getAllWork(request)
    }

    @ApiOperation(value="work정보", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    @GetMapping("info/{workId}")
    fun getWorkInfo(request: HttpServletRequest,@PathVariable workId : String) : Any{
        println("work/info/${workId}")
        return service.getWorkInfo(request,workId)
    }

    @ApiOperation(value="북마크저장", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 신규:1 업데이트 :2\n-100 : DB에러"))
    @PostMapping("savebookmark/{workId}")
    fun saveBookMark(
            request: HttpServletRequest,
            @PathVariable workId : String,
            @RequestParam epiId : String,
            @RequestParam sceneId : String,
            @RequestParam pageId : String
    ): Int{
        println("work/savebookmark/${workId}")
        return service.saveBookMark(request,workId,epiId,sceneId,pageId)
    }

    @ApiOperation(value="work,cast생성", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n-200 : title누락"))
    @PostMapping("creatework")
    fun createWork(
            request : HttpServletRequest,
            @RequestBody @ApiParam("work객체",required = true) work: Work
    ) : Any{
        println("work/creatework")
        return service.createWork(request, work)
    }

    @PostMapping("/updatework")
    @ApiOperation(value="work수정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun updateWork(
            request : HttpServletRequest,
            @RequestBody @ApiParam("work객체",required = true) work : Work
    ) : Any{
        println("work/updatework")
        return service.updateWork(request, work)
    }

    @PostMapping("/savework")
    @ApiOperation(value="work,var저장", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun saveWork(
            request : HttpServletRequest,
            @RequestBody @ApiParam("work객체",required = true) work : Work
    ) : Any{
        println("work/savework")
        return service.saveWork(request, work)
    }

    @GetMapping("release/{workId}")
    @ApiOperation(value="공개된work이하 조회")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getWorkByRelease(
            request: HttpServletRequest,
            @PathVariable workId : String
    ) : Any?{
        println("work/release/${workId}")
        return service.getWorkByRelease(request,workId)
    }

    @GetMapping("releaseobbrabo")
    @ApiOperation(value="좋아요순 공개된work이하 조회")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getWorkByReleaseBrabo() : Any?{
        println("work/releaseobbrabo")
        return service.getWorkByReleaseObBrabo()
    }

    @GetMapping("releaseobtime")
    @ApiOperation(value="최신순 공개된work이하 조회")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getWorkByReleaseTime() : Any?{
        println("work/releaseobtime")
        return service.getWorkByReleaseObTime()
    }

    @GetMapping("getworkbrabo")
    @ApiOperation(value="내가 좋아요한 work 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getWorkBrabo(request: HttpServletRequest) : Any{
        println("work/getworkbrabo")
        return service.getWorkBrabo(request)
    }

    @GetMapping("getworkall")
    @ApiOperation(value="나의work이하 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Json\n-100 : DB에러"))
    fun getWorkAll(request: HttpServletRequest) : Any{
        println("work/getworkall")
        return service.getWorkAll(request)
    }

    @GetMapping("getworkpopular")
    @ApiOperation(value="나의work인기도 조회", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Json\n-100 : DB에러"))
    fun getWorkPopular(request: HttpServletRequest) : Any{
        println("work/getworkpopular")
        return service.getWorkPopular(request)
    }

    @PostMapping("theend/{workId}")
    @ApiOperation(value="work 완결여부수정", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러\n" +
            "-1 : 완결불가"))
    fun theEnd(request: HttpServletRequest , @PathVariable workId : String) : Any{
        return service.theEnd(request,workId)
    }
}