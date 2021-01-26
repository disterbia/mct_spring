package com.music961.pinto.service

import com.music961.pinto.model.work.*
import com.music961.pinto.repository.*
import org.springframework.stereotype.Service
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@Service
class WorkService (
        val workRepo    : WorkRepository,
        val epiRepo    : EpiRepository,
        val jwtService  : MyJwtService,
        val fileService : FileService,
        val genreRepo: GenreRepository,
        val workgenreRepo : WorkGenreRepository,
        val varRepo : VarRepository,
        val sceneRepo : SceneRepository,
        val optRepo : OptRepository,
        val pageRepo : PageRepository,
        val viewService : ViewService,
        val castRepo: CastRepository){

    fun deleteWork(request: HttpServletRequest, work: Work) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        println(usrId)
        try {
            val result = workRepo.deleteWorkCascade(work.id, usrId)
            println("==$result")
            if(result > 0){
                println("deleteWork return -> 100")
                return 100
            }else{
                println("deleteWork return -> -1")
                return -1}

        }catch (e : Exception){
            e.printStackTrace()
            println("deleteWork return -> -100")
            return -100
        }
    }

    fun getAllWork(request: HttpServletRequest) : Any {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = workRepo.getAllWork()
            println("getAllWork return -> $result")
           return result
        }catch (e: Exception){
            e.printStackTrace()
            println("getAllWork return -> -100")
            return -100
        }
    }

    fun saveBookMark(request: HttpServletRequest , workId : String , epiId : String , sceneId : String ,pageId : String) :Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = workRepo.saveBookMark(usrId,workId.toLong(),epiId.toLong(),sceneId.toLong(),pageId.toLong())
            println("saveBookMark return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("saveBookMark return -> -100")
            return -100
        }
    }

    fun getWorkInfo(request: HttpServletRequest,workId : String) : Any {
        val usrId = jwtService.getUsrIdByJWT(request)
        try{
            val work = workRepo.getWorkInfo(usrId,workId.toLong())
            val array = mutableListOf<String>()
            if(work.size > 1){
                work.forEach {
                    array.add(it.genreValue)
                }
                for(i in 1 until array.size){
                    work[0].genreValue +=","+array[i]
                }
            }
            println("getWorkInfo return -> ${work[0]}")
            return work[0]
        }catch (e: Exception){
            e.printStackTrace()
            println("getWorkInfo return -> -100")
            return -100
        }
    }

    fun getMyWork(request: HttpServletRequest) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val workList = workRepo.getMyWork(usrId)
            workList.forEach { work->
                val gs = genreRepo.getGenre(work.id)
                work.genres = gs
            }
            println("getMyWork return -> $workList")
            return workList
        }catch (e : Exception){
            e.printStackTrace()
            println("getMyWork return -> -100")
            return -100
        }
    }

    fun createWork(request : HttpServletRequest, work: Work) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        if(work.title =="") return -200
        val fileBase64Encoded = work.faceFileBase64Encoded
        work.face = if(fileBase64Encoded == "") 0 else 1
        work.lastedit = Instant.now().epochSecond.toInt()
        work.usrKey = usrId
        try {
            workRepo.saveWork(work)
            if(work.genres.size > 0){
                work.genres.forEach {
                    it.workId = work.id
                    it.genreId = 0
                    genreRepo.saveGenre(it)
                    workgenreRepo.saveWorkGenre(it)
                }
             }
            val cast= Cast(work.id,10000,usrId,1,-100,"",1.00,0.5,0.5)
            work.casts.add(cast)
            if(fileBase64Encoded != ""){
                fileService.saveFileFromBase64String(work.id.toString(), work.faceFileBase64Encoded,2)
                work.face = work.id
                work.faceFileBase64Encoded = ""
            }
            println("createWork return -> $work")
            return work
        }catch (e : Exception){
            e.printStackTrace()
            println("createWork return -> -100")
            return -100
        }
    }

    fun updateWork(request : HttpServletRequest, work : Work) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        work.usrKey = usrId
        try {
            if(work.faceFileBase64Encoded != ""){
                fileService.saveFileFromBase64String(work.id.toString(), work.faceFileBase64Encoded,2)
                work.face = work.id
                work.faceFileBase64Encoded = ""
            }
            if(work.faceFileBase64Encoded == "" && work.face == 0L){
                fileService.deleteFile(work.id.toString(),2)
            }
            work.lastedit = Instant.now().epochSecond.toInt()
            workRepo.updateWork(work)

            if(work.genres.size > 0){
                workgenreRepo.deleteWorkGenreByWorkId(work.id)
                work.genres.forEach {
                    it.workId = work.id
                    it.genreId = 0
                    genreRepo.saveGenre(it)
                    workgenreRepo.saveWorkGenre(it)
                }
            }
            println("updateWork return -> $work")
            return work
        }catch (e : Exception){
            e.printStackTrace()
            println("updateWork return -> -100")
            //fileService.deleteFile(work.face)
            return -100
        }
    }

    fun saveWork(request : HttpServletRequest, work : Work) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        work.usrKey = usrId
        try {
//            if(!(work.face.equals(""))){
//                val fileName = "${System.currentTimeMillis()}_${work.usrKey}"
//                fileService.saveFileFromBase64String(fileName, work.face)
//                work.face = fileName
//            }
            workRepo.saveWork(work)
            if(work.vars.size > 0){
                varRepo.deleteVarByWorkId(work.id)
                work.vars.forEach {
                    it.workId = work.id
                    it.varId = 0
                    varRepo.saveVar(it)
                }
            }
            if(work.genres.size > 0){
                workgenreRepo.deleteWorkGenreByWorkId(work.id)
                work.genres.forEach {
                    it.workId = work.id
                    it.genreId = 0
                    genreRepo.saveGenre(it)
                    workgenreRepo.saveWorkGenre(it)
                }
            }
            println("saveWork return -> $work")
            return work
        }catch (e : Exception){
            e.printStackTrace()
            println("saveWork return -> -100")
            //fileService.deleteFile(work.face)
            return -100
        }
    }

    fun getWorkByRelease(request: HttpServletRequest,id : String) : Any? {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val work      = workRepo.getWorkByRelease(usrId,id.toLong())
            val epiList   = epiRepo.getEpiList(work?.id)
            val sceneList = sceneRepo.getSceneList(work?.id)
            val pageList  = pageRepo.getPageList(work?.id)
            val optList   = optRepo.getOptList(work?.id)

            for (page in pageList){
                for (opt in optList){
                    if(page.workId == opt.workId && page.epiId == opt.epiId && page.sceneId == opt.sceneId && page.pageId == opt.pageId){
                        page.opts.add(opt)
                    }
                }
            }

            for (scene in sceneList){
                for (page in pageList){
                    if(scene.workId == page.workId && scene.epiId == page.epiId && scene.sceneId == page.sceneId){
                        scene.pages.add(page)
                    }
                }
            }

            for (epi in epiList){
                for (scene in sceneList){
                    if(epi.workId == scene.workId && epi.epiId == scene.epiId){
                        epi.scenes.add(scene)
                    }
                }
            }
            work?.epis = epiList
            viewService.saveViewInfo(usrId,id.toInt())
            println("getWorkByRelease return -> $work")
            return work
        }catch (e : Exception){
            e.printStackTrace()
            println("getWorkByRelease return -> -100")
            return -100
        }
    }

    fun getWorkByReleaseObBrabo() : Any? {
        try {
            val result = workRepo.getWorkByReleaseObBrabo()
            println("getWorkByReleaseObBrabo return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getWorkByReleaseObBrabo return -> -100")
            return -100
        }
    }

    fun getWorkByReleaseObTime() : Any? {
        try {
            val result = workRepo.getWorkByReleaseObTime()
            println("getWorkByReleaseObTime return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getWorkByReleaseObTime return -> -100")
            return -100
        }
    }

    fun getWorkBrabo(request: HttpServletRequest) : Any {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = workRepo.getWorkBrabo(usrId)
            println("getWorkBrabo return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getWorkBrabo return -> -100")
            return -100
        }
    }

    fun getWorkAll(request: HttpServletRequest) : Any {
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val workList = workRepo.getMyWork(usrId)
            if(workList.size == 0) {
                return workList
            }
            workList.forEach { work->
                val gs = genreRepo.getGenre(work.id)
                work.genres = gs
            }
            val epiList  = epiRepo.getEpiListAll(workList)
            val sceneList = sceneRepo.getSceneListAll(workList)
            val pageList = pageRepo.getPageListAll(workList)
            val optList  = optRepo.getOptListAll(workList)
            val castList = castRepo.getCastListAll(workList)

            for (page in pageList){
                for (opt in optList){
                    if(page.workId == opt.workId && page.epiId == opt.epiId && page.sceneId == opt.sceneId && page.pageId == opt.pageId){
                        page.opts.add(opt)
                    }
                }
            }

            for (scene in sceneList){
                for (page in pageList){
                    if(scene.workId == page.workId && scene.epiId == page.epiId && scene.sceneId == page.sceneId){
                        scene.pages.add(page)
                    }
                }
            }

            for (epi in epiList){
                for (scene in sceneList){
                    if(epi.workId == scene.workId && epi.epiId == scene.epiId){
                        epi.scenes.add(scene)
                    }
                }
            }
            for (work in workList){
                for (epi in epiList){
                    if(work.id == epi.workId){
                        work.epis.add(epi)
                    }
                }
                for (cast in castList){
                    if(work.id == cast.workId)
                        work.casts.add(cast)
                }
            }
            println("getWorkAll return -> $workList")
            return workList
        }catch (e : Exception){
            e.printStackTrace()
            println("getWorkAll return -> -100")
            return -100
        }
    }

    fun getWorkPopular(request: HttpServletRequest) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result = workRepo.getWorkPopular(usrId)
            println("getWorkPopular return -> $result")
            return result
        }catch (e:Exception){
            e.printStackTrace()
            println("getWorkPopular return -> -100")
            return -100
        }
    }

    fun theEnd(request: HttpServletRequest , workId : String) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result =workRepo.theEnd(usrId,workId.toLong())
            if(result == 1) return 100
            else return -1
        }catch (e : Exception){
            e.printStackTrace()
            return -100
        }
    }

//    fun deleteWork( request : HttpServletRequest, workId : String ) : Int{
//        val usrId = jwtService.getUsrIdByJWT(request)
//        if(usrId < 1){
//            return -403
//        }
//        try {
//            val deletedRowCount = workRepo.deleteWorkCascade(workId.toLong(), usrId)
//            return deletedRowCount * 10
//        }catch (e : Exception){
//            e.printStackTrace()
//            return -100
//        }
//    }
}