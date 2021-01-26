package com.music961.pinto.service

import com.music961.pinto.model.work.*
import com.music961.pinto.repository.WorkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.Instant
import javax.servlet.http.HttpServletRequest

//@Service
//class WorkTestService(val workRepo : WorkRepository,
//                      val fileService : FileService){
//
//    fun updateWork(request : HttpServletRequest, work : Work
//    ) : Any{
////        val usrId = getUsrIdByJWT(request)
////        if(usrId < 1){
////            return -403
////        }
////        work.usrKey = usrId
//        work.usrKey = 16
//        try {
//            if(!(work.faceFileBase64Encoded.equals(""))){
//                val fileName = "${System.currentTimeMillis()}_${work.usrKey}"
//                fileService.saveFileFromBase64String(fileName, work.faceFileBase64Encoded)
//                work.face = fileName
//                work.faceFileBase64Encoded = ""
//            }
//            work.lastedit = Instant.now().epochSecond.toInt()
//            workRepo.saveWork(work)
////            if(work.vars.size > 0){
////                workRepo.deleteVarByWorkId(work.id)
////                work.vars.forEach {
////                    it.workId = work.id
////                    it.varId = 0
////                    workRepo.saveVar(it)
////                }
////            }
//            if(work.genres.size > 0){
//                workRepo.deleteWorkGenreByWorkId(work.id)
//                work.genres.forEach {
//                    it.workId = work.id
//                    it.genreId = 0
//                    workRepo.saveGenre(it)
//                    workRepo.saveWorkGenre(it)
//                }
//            }
//            return work
//        }catch (e : Exception){
//            e.printStackTrace()
//            //fileService.deleteFile(work.face)
//            return -100
//        }
//    }
//    fun createWork(request : HttpServletRequest) : Any{
////        val usrId = getUsrIdByJWT(request)
////        if(usrId < 1){
////            return -403
////        }
//        val work = Work(0,"새로운 이야기", Instant.now().epochSecond.toInt(),"", 16)
//        try {
//            workRepo.saveWork(work)
//            return work
//        }catch (e : Exception){
//            e.printStackTrace()
//            return -100
//        }
//    }
//
//    fun getEpi() : Epi{
//        val epi = Epi(5,0,"Test", "fff", 1, ArrayList<Scene>(), 16)
//        epi.face = fileService.loadFileToBase64String("43").toString()
//        epi.scenes.add(Scene(5,0,1,"test1",123456,2,1,"note"))
//        epi.scenes.add(Scene(5,0,2,"test2",123456,2,1,"note"))
//        epi.scenes.add(Scene(5,0,3,"test3",123456,2,1,"note"))
//        epi.scenes.forEach { scene->
//            scene.pages.add(Page(5,0,scene.sceneId, 1))
//            scene.pages.add(Page(5,0,scene.sceneId, 2))
//            scene.pages.add(Page(5,0,scene.sceneId, 3))
//            scene.pages.forEach { page->
//                page.opts.add(Opt(5,0,scene.sceneId, page.pageId, 1, 11,"test"))
//                page.opts.add(Opt(5,0,scene.sceneId, page.pageId, 2, 11,"test"))
//                page.opts.add(Opt(5,0,scene.sceneId, page.pageId, 3, 11,"test"))
//                page.opts.add(Opt(5,0,scene.sceneId, page.pageId, 4, 11,"test"))
//            }
//        }
//        return epi
//    }
//
//    @Transactional
//    fun saveEpi(epi : Epi) : Any{
////        val usrId = getUsrIdByJWT(request)
////        if(usrId < 1){
////            return -403
////        }
////
////        epi.usrId = usrId
//        try {
//            if(!(epi.face.equals(""))){
//                val fileName = "${System.currentTimeMillis()}_${epi.usrId}"
//                fileService.saveFileFromBase64String(fileName, epi.face)
//                epi.face = fileName
//            }
//            workRepo.saveEpi(epi)
//
//            val scenesSize = epi.scenes.size
//            //여기에 deleteSceneByWorkId
//
//            for(i in 0..scenesSize - 1){
//                epi.scenes[i].epiId = epi.epiId
//                epi.scenes[i].pages.forEach { page->
//                    page.epiId = epi.epiId
//                    page.opts.forEach { opt->
//                        opt.epiId = epi.epiId
//                    }
//                }
//            }
//            workRepo.saveScenes(epi.scenes)
//            epi.scenes.forEach { scene->
//                workRepo.savePages(scene.pages)
//            }
//
//            epi.scenes.forEach { scene->
//                scene.pages.forEach { page->
//                    workRepo.saveOpts(page.opts)
//                }
//            }
//            epi.releaseYn = 0
//            return epi
//        }catch (e : Exception){
//            fileService.deleteFile(epi.face)
//            throw RuntimeException(e)
//        }
//    }
//
////    fun savePage(page : Page) : Any{
////        try {
////            workRepo.savePage(page)
////            return page
////        }catch (e : Exception){
////            e.printStackTrace()
////            return page
////        }
////    }
////
////    fun saveScene(scene : Scene) : Any{
////        try {
////            workRepo.saveScene(scene)
////            return scene
////        }catch (e : Exception){
////            e.printStackTrace()
////            return scene
////        }
////    }
////
////    fun ftest(genre: Genre) : String{
////        try {
////            workRepo.saveWorkGenre(genre)
////            return "Suc"
////        }catch (e : Exception){
////            e.printStackTrace()
////            return "Fai"
////        }
////    }
////
////    fun test() : Work{
////        val work = Work(0,"test", 20202020, "testest", 16)
////        work.vars.add(Var(0, 0, 7, "testtest"))
////        work.vars.add(Var(0, 0, 8, "testtest"))
////        work.vars.add(Var(0, 0, 9, "testtest"))
////        work.vars.add(Var(0, 0, 10, "testtest"))
////        work.vars.add(Var(0, 0, 11, "testtest"))
////        work.vars.add(Var(0, 0, 12, "testtest"))
////        work.vars.add(Var(0, 0, 13, "testtest"))
////
////        work.genres.add(Genre(0,0,"BL"))
////        work.genres.add(Genre(0,0,"로맨스"))
////        work.genres.add(Genre(0,0,"액션"))
////        work.genres.add(Genre(0,0,"느와르"))
////        work.genres.add(Genre(0,0,"멜"))
////        return work
////    }
////
////    fun gg2(genre : Genre) : Genre{
////        workRepo.saveGenre(genre)
////        return genre
////    }
////
////    fun test2() : ArrayList<Var>{
////        val lst = ArrayList<Var>()
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////        lst.add(Var(2, 0, 7, "testtest"))
////
////        return lst
////    }
////
////    fun varTest(v : Var) : Var{
////        workRepo.saveVar(v)
////        return v
////    }
////
////    fun deleteWork2(request : HttpServletRequest, workId : String) : Int{
////        try {
////            val deletedRowCount = workRepo.deleteWorkCascade(workId.toLong(), 16)
////            return deletedRowCount * 10
////        }catch (e : Exception){
////            e.printStackTrace()
////            return -100
////        }
////    }
////
////    fun saveWork(request : HttpServletRequest, work : Work) : Any{
////        try {
////            workRepo.saveWork(work)
////            if(work.vars.size > 0){
////                workRepo.deleteVarByWorkId(work.id)
////                work.vars.forEach {
////                    it.workId = work.id
////                    it.varId = 0
////                    workRepo.saveVar(it)
////                }
////            }
////            if(work.genres.size > 0){
////                workRepo.deleteWorkGenreByWorkId(work.id)
////                work.genres.forEach {
////                    it.workId = work.id
////                    it.genreId = 0
////                    workRepo.saveGenre(it)
////                    workRepo.saveWorkGenre(it)
////                }
////            }
////            return work
////        }catch (e : Exception){
////            e.printStackTrace()
////            return -100
////        }
////    }
//}
