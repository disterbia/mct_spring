package com.music961.pinto.controller

import com.music961.pinto.model.User
import com.music961.pinto.repository.TestRepository
import com.music961.pinto.service.FileService
import com.music961.pinto.service.MyJwtService
import com.music961.pinto.service.TestService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.swagger.annotations.Api
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import kotlin.collections.LinkedHashMap

@RestController
@RequestMapping("/test")
class TestController (val testService: TestService,
                      val fileService: FileService,
                      val testRepo   : TestRepository,
                      val jwtService : MyJwtService){

    @GetMapping("/tag1")
    fun tag1() : Any{
        return "#태그1#태그2#태그3".split("#")
    }


    @GetMapping("/tag/{fileName}")
    fun list(
            @PathVariable fileName : Int
    ) : Any{
       return testService.list(fileName)
    }

    // 업로드 테스트 OK
    @PostMapping("/upload")
    fun upload(
            @RequestParam("file") file : MultipartFile
    ): Int{
        return fileService.saveFile("1", file)
    }

    //테스트 완
    @GetMapping("/down/{fileName}")
    fun down(
            @PathVariable fileName : String,
            request : HttpServletRequest
    ): ResponseEntity<Resource>?{
        return testService.down(fileName,request)
    }

    //테스트 완료
    @PostMapping("/delete")
    fun delete(file : String){
        fileService.deleteFile(file,0)
    }






    //테스트 완
    @GetMapping("/downtest/{fileName}")
    fun downtest(
            @PathVariable fileName : String,
            request : HttpServletRequest
    ): ResponseEntity<Resource>?{
       return testService.downtest(fileName, request)
    }

    @GetMapping("/d1")
    fun d1() : Any{
        return fileService.loadFileToBase64String("23",0)
    }
    @GetMapping("/d2")
    fun d2() : Any{
        return fileService.loadFileToBase64String("2",0)
    }
    @PostMapping("/u1")
    fun u1(fileName: String,
            file : String) : Int{
        return fileService.saveFileFromBase64String(fileName,file,0)
    }

    @GetMapping(
            value = ["/t1"]
    )
    fun t1() : ByteArray {
        var file = fileService.file("1",0)
        return file.inputStream().readAllBytes()
    }

    @GetMapping("t2")
    fun t2() : ByteArray {
        var file = fileService.file("1",0)
        var byteArr = file.inputStream().readAllBytes()

        return Base64.encodeBase64(byteArr)
    }


    @GetMapping("/test")
    fun test(): String{
        var user = User(1,"test@test.com","test",1,1)
        return jwtService.makeToken(user)
    }

    @PostMapping("/test")
    fun test2(token : String): Jws<Claims>? {
        return jwtService.getDecodeToken(token)
    }
//    @PostMapping("/test2")
//    fun test3(token : String): Boolean {
//
//        return jwtService.isUseableToken(token)
//    }

    @GetMapping("/ttt")
    fun ttt() : LinkedHashMap<String, Any>{
        return testService.ttt()
    }

    @GetMapping("/tem")
    fun tem() : Int{
        try {
            return testRepo.selectTestCnt()
        }catch (e:Exception){
            return -1
        }
    }
}