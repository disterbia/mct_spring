package com.music961.pinto.controller

import com.music961.pinto.service.FileService
import io.swagger.annotations.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/file")
@Api(tags=["FileContorller"])
class FileController(val fileService: FileService) {

    @GetMapping(
            value = ["/{fileName}"],
            produces = [MediaType.IMAGE_PNG_VALUE]
    )
    @ApiOperation(value="파일가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ByteArray"))
    fun splash(@PathVariable fileName : String) : ByteArray{
        println("file/${fileName}")
        return fileService.file(fileName,0).readBytes()
    }
    @GetMapping(
            value = ["/profile/{fileName}"],
            produces = [MediaType.IMAGE_PNG_VALUE]
    )
    @ApiOperation(value="유저 프로필파일 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ByteArray"))
    fun profile(@PathVariable fileName : String) : ByteArray{
        println("file/profile/${fileName}")
        return fileService.file(fileName,1).readBytes()
    }

    @GetMapping(
            value = ["/work/{fileName}"],
            produces = [MediaType.IMAGE_PNG_VALUE]
    )
    @ApiOperation(value="work face파일 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ByteArray"))
    fun workFace(@PathVariable fileName : String) : ByteArray{
        println("file/work/${fileName}")
        return fileService.file(fileName,2).readBytes()
    }

    @GetMapping(
            value = ["/epi/{fileName}"],
            produces = [MediaType.IMAGE_PNG_VALUE]
    )
    @ApiOperation(value="epi face파일 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ByteArray"))
    fun epiFace(@PathVariable fileName : String) : ByteArray{
        println("file/epi/${fileName}")
        return fileService.file(fileName,3).readBytes()
    }
}