package com.music961.pinto.service

import com.music961.pinto.model.work.Page
import com.music961.pinto.model.work.Scene
import com.music961.pinto.repository.PageRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class PageService (
            val pageRepo : PageRepository,
            val jwtService: MyJwtService){

    fun getPages(request: HttpServletRequest,workId : Long , epiId : Long , sceneId : Long) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val pages = pageRepo.getPages(workId,epiId,sceneId)
            for (page in pages){
                page.usrId= usrId
            }
            println("getPages return -> $pages")
            return pages
        }catch(e : Exception){
            e.printStackTrace()
            println("getPages return -> -100")
            return -100
        }
    }

    fun createPage(request: HttpServletRequest,page: Page) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            page.usrId = usrId
            pageRepo.createPage(page)
            println("createPage return -> $page")
            return page
        }catch(e : Exception){
            e.printStackTrace()
            println("createPage return -> -100")
            return -100
        }
    }
    fun deletePage(request : HttpServletRequest, page : Page) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        page.usrId = usrId
        try {
            val result =pageRepo.deletePage(page)
            if(result < 1){
                println("deletePage return -> -999")
                return -999
            }
            println("deletePage return -> 100")
             return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("deletePage return -> -100")
            return -100
        }
    }

}