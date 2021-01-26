package com.music961.pinto.controller

import com.music961.pinto.service.AdminService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/admin")
@Api(tags=["AdminContorller"])
class AdminController(val adminService : AdminService) {

    private val authString = "difpdifp486$"

    @PostMapping("/musicupload")
    @ApiOperation(value="관리자 파일업로드")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : String"))
    fun musicUpload(
            @RequestParam("name")  @ApiParam("이름",required = true) name  : String,
            @RequestParam("note")  @ApiParam("노트",required = true)note  : String,
            @RequestParam("price") @ApiParam("가격",required = true)price : String,
            @RequestParam("tags")  @ApiParam("태그",required = true)tags  : String,
            @RequestParam("pass")  @ApiParam("관리자번호",required = true)pass  : String,
            @RequestParam("user")  @ApiParam("유저키",required = true)user  : String,
            @RequestParam("file")  @ApiParam("파일",required = true)file  : MultipartFile
    ) : String{

        if(pass != authString){
            return "Fail:Check admin authString"
        }
        println("musicupload")
        return adminService.musicUpload(name,note,price,tags,pass,user,file)
    }
}