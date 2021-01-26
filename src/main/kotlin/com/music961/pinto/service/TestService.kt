package com.music961.pinto.service

import com.music961.pinto.model.User
import com.music961.pinto.repository.ResRepository
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.LinkedHashMap

@Service
class TestService (val fileService: FileService, val resRepo : ResRepository) {

    fun list(fileName : Int) : Any {
        var res = resRepo.getResource(15,fileName)
        if(res == null){
            return -2
        }//res.fileName.toString()
        var file = fileService.loadFileToBase64String(fileName.toString(),0)
        if(file == -404 || file == -100)return file

        res.file = file as String

        return res
    }

    fun down(fileName : String, request : HttpServletRequest): ResponseEntity<Resource>?{
        var resource =  fileService.loadFile(fileName,0)
        if(resource is Resource){
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource)
        }else{
            return null
        }
    }


    fun downtest(fileName : String, request : HttpServletRequest): ResponseEntity<Resource>?{
        println(request.getHeader("TOKEN"))
        var resource =  fileService.loadFile(fileName,0)
        if(resource is Resource){
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource)
        }else{
            return null
        }
    }
    fun ttt() : LinkedHashMap<String, Any>{
        println(Date().time)
        val test = LinkedHashMap<String, Any>()
        test.put("code1", "code2")
        test.put("token", "sdgsdgjksdgsdlgsdkglsdgjsdlgjsklgjskd")
        test.put("user", User(1,"rrr","name",100,200))
        println(Date().time)
        return test
    }
}