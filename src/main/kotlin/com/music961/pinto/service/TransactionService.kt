package com.music961.pinto.service

import com.music961.pinto.model.work.Epi
import com.music961.pinto.model.work.Opt
import com.music961.pinto.model.work.Page
import com.music961.pinto.model.work.Scene
import com.music961.pinto.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.Instant

@Service
class TransactionService (
        val pageRepo : PageRepository,
        val optRepo : OptRepository,
        val epiRepo : EpiRepository,
        val sceneRepo : SceneRepository){

    //Scene 이하 Page, Opt 저장로직
    @Transactional
    fun senceTrans(scene: Scene){
        try {
            if(scene.pages.size > 0){
                pageRepo.deletePage(scene.pages[0])
            }
            pageRepo.savePages(scene.pages)
            val list = scene.pages
            val optList = ArrayList<Opt>()
            for (page in list){
                optList.addAll(page.opts)
            }
            optRepo.saveOpts(optList)
        }catch (e :Exception){
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }

    @Transactional
    fun sceneTrans(epi : Epi) : Any{
        try {
            epiRepo.saveEpi(epi)
            epi.releaseYn = 0
            val newScene = Scene().apply {
                workId = epi.workId
                epiId  = epi.epiId
                usrId  = epi.usrId
                name   = "새로운 선택지"
                lastedit = Instant.now().epochSecond.toInt()
            }

            sceneRepo.saveSceneWaterFall(newScene)

            val newPage = Page().apply{
                workId = epi.workId
                epiId  = epi.epiId
                sceneId = newScene.sceneId
                usrId  = epi.usrId
            }

            pageRepo.createPage(newPage)

            newScene.pages.add(newPage)
            epi.scenes.add(newScene)
            println("sceneTrans return -> $epi")
            return epi
        }catch (e : Exception){
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }

    @Transactional
    fun epiTrans(epi: Epi){
        try {
            epiRepo.deleteEpiCascade(epi)
            epiRepo.saveEpi(epi)
            val scenesSize = epi.scenes.size
            //여기에 deleteSceneByWorkId

            for(i in 0..scenesSize - 1){
                epi.scenes[i].epiId = epi.epiId
                epi.scenes[i].pages.forEach { page->
                    page.epiId = epi.epiId
                    page.opts.forEach { opt->
                        opt.epiId = epi.epiId
                    }
                }
            }

            sceneRepo.saveScenes(epi.scenes)
            epi.scenes.forEach { scene->
                pageRepo.savePages(scene.pages)
            }

            epi.scenes.forEach { scene->
                scene.pages.forEach { page->
                    optRepo.saveOpts(page.opts)
                }
            }
            epi.releaseYn = 0
        }catch (e : Exception){
            throw RuntimeException(e)
        }
    }
}