package com.music961.pinto.service

import com.music961.pinto.config.FileConfig
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.math.absoluteValue

@Service
class FileService(fileConfig: FileConfig){

    final val filePath : Path = Paths.get(fileConfig.dir)
            .toAbsolutePath()
            .normalize()
    final val profileFilePath : Path = Paths.get(fileConfig.dir+"/userProfile")
            .toAbsolutePath()
            .normalize()
    final val workFilePath : Path = Paths.get(fileConfig.dir+"/workProfile")
            .toAbsolutePath()
            .normalize()
    final val epiFilePath : Path = Paths.get(fileConfig.dir+"/epiProfile")
            .toAbsolutePath()
            .normalize()

    init {
        try {
            Files.createDirectories(filePath)
            Files.createDirectories(profileFilePath)
            Files.createDirectories(workFilePath)
            Files.createDirectories(epiFilePath)
            println("디렉토리 생성 성공")
        }catch (e : Exception){
            println("디렉토리 생성 실패")
            e.printStackTrace()
        }
    }


    fun getFileSize(fileName : String) : Long{
        val file = File(filePath.toString(), fileName)
        val fileOriginSize = file.length().absoluteValue
        println("getFileSize return -> $fileOriginSize")
        return fileOriginSize
    }

    //base64 -> file
    fun saveFileFromBase64String(
            fileName          : String,
            fileBase64Encoded : String,
            cat               : Int
    ) : Int{
        val target : Path
        if(cat == 0) {
             target = filePath.resolve(fileName)
        }else if(cat ==1) {
             target = profileFilePath.resolve(fileName)
        }else if(cat ==2) {
             target = workFilePath.resolve(fileName)
        }else if(cat ==3) {
             target = epiFilePath.resolve(fileName)
        }
        else return -300
        try {
            Files.copy(Base64.decodeBase64(fileBase64Encoded).inputStream(), target, StandardCopyOption.REPLACE_EXISTING)
            println("saveFileFromBase64String return -> 100")
            return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("saveFileFromBase64String return -> -100")
            return -100
        }
    }

    //file -> base64
    fun loadFileToBase64String(fileName: String,cat : Int) : Any{
        val target : Path
        if(cat == 0) {
             target = filePath.resolve(fileName).normalize()
        }
        else if(cat ==1) {
             target = profileFilePath.resolve(fileName).normalize()
        }else if(cat ==2) {
             target = workFilePath.resolve(fileName)
        }else if(cat ==3) {
             target = epiFilePath.resolve(fileName)
        }
        else return -200
        try {
            val resource = UrlResource(target.toUri())
            if(resource.exists()){
                return Base64.encodeBase64String(resource.file.inputStream().readBytes())
            }
            println("파일 없음")
            println("loadFileToBase64String return -> -200")
            return -200
        }catch (e : Exception){
            println("파일 로딩 에러")
            println("loadFileToBase64String return -> -100")
            e.printStackTrace()
            return -100
        }
    }


    fun saveFile(
            fileName : String,
            file : MultipartFile
    ) : Int {
        try {
            val target = filePath.resolve(fileName)
            Files.copy(file.inputStream, target, StandardCopyOption.REPLACE_EXISTING)
            println("saveFile return -> 100")
            return 100
        }catch (e : Exception){
            println("파일 저장 에러")
            println("saveFile return -> -100")
            e.printStackTrace()
            return -100
        }
    }

    fun loadFile(fileName : String,cat : Int) : Any {
        val target : Path
        if(cat == 0) {
             target = filePath.resolve(fileName).normalize()
        }
        else if(cat ==1) {
             target = profileFilePath.resolve(fileName).normalize()
        }else if(cat ==2) {
             target = workFilePath.resolve(fileName)
        }else if(cat ==3) {
             target = epiFilePath.resolve(fileName)
        }else return -200
        try {
            val resource = UrlResource(target.toUri())
            if(resource.exists()){
                println("loadFile return -> $resource")
                return resource
            }
            println("파일 없음")
            println("loadFile return -> -404")
            return -404
        }catch (e : Exception){
            println("파일 로딩 에러")
            println("loadFile return -> -100")
            e.printStackTrace()
            return -100
        }
    }

    fun file(fileName : String,cat : Int) : File{
        val target : Path
        if(cat == 0) {
             target = filePath
        }
        else if(cat ==1) {
             target = profileFilePath
        }
        else if(cat ==2) {
             target = workFilePath
        }else if(cat ==3) {
             target = epiFilePath
        }else return File("카테고리 잘못됨")
        println("file return -> ${File(target.toString(), fileName)}")
        return File(target.toString(), fileName)
    }

    fun deleteFile(fileName: String,cat : Int) : Int{
        val target : Path
        if(cat == 0) {
             target = filePath.resolve(fileName).normalize()
        }
        else if(cat ==1) {
             target = profileFilePath.resolve(fileName).normalize()
        }
        else if(cat ==2) {
             target = workFilePath.resolve(fileName)
        }else if(cat ==3) {
             target = epiFilePath.resolve(fileName)
        }else return -300
        try {
            val resource = UrlResource(target.toUri())
            if(resource.exists()){
                resource.file.delete()
                println("deleteFile return -> 100")
                return 100
            }
            println("파일 없음")
            println("deleteFile return -> -404")
            return -404
        }catch (e : Exception){
            println("파일 로딩 에러")
            println("deleteFile return -> -100")
            e.printStackTrace()
            return -100
        }
    }

}