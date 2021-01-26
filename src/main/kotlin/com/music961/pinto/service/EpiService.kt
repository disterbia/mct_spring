package com.music961.pinto.service

import com.music961.pinto.model.work.Epi
import com.music961.pinto.repository.EpiRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class EpiService (
        val epiRepo : EpiRepository,
        val jwtService: MyJwtService,
        val fileService: FileService,
        val transactionService: TransactionService
){
    fun getMyEpi(request: HttpServletRequest, workId : String) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        try {
            val result =epiRepo.getMyEpi(workId.toLong())
            println("getMyEpi return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("getMyEpi return -> -100")
            return -100
        }
    }

    fun createEpi(request : HttpServletRequest, pworkId : String) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        try {
            val newEpi = Epi().apply {
                workId = pworkId.toLong()
                usrId = id
                title = "새로운 에피소드"
            }
            epiRepo.saveEpi(newEpi)
            println("createEpi return -> $newEpi")
            return newEpi
        }catch (e : Exception){
            e.printStackTrace()
            println("createEpi return -> -100")
            return -100
        }
    }

    fun createEpiWaterFall(request: HttpServletRequest, epi : Epi) : Any {
        val id = jwtService.getUsrIdByJWT(request)
        epi.usrId = id
        try {
             return transactionService.sceneTrans(epi)
        }catch (e : Exception){
            e.printStackTrace()
            println("createEpiWaterFall return -> -100")
            return -100
        }
    }

    fun deleteEpi(request : HttpServletRequest, epi : Epi) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        epi.usrId = usrId
        try {
            val result =epiRepo.deleteEpiCascade(epi)
            if(result < 1){
                println("deleteEpi return -> -999")
                return -999
            }
            println("deleteEpi return -> 100")
             return 100
        }catch (e : Exception){
            println("deleteEpi return -> -100")
            return -100
        }
    }

    fun updateEpi(request : HttpServletRequest, epi : Epi) : Int{
        val usrId = jwtService.getUsrIdByJWT(request)
        //work.usrKey = usrId
        epi.usrId = usrId
        try {
//            if(!(epi.faceFileBase64Encoded.equals(""))){
//                val fileName = "${System.currentTimeMillis()}_${epi.usrId}"
//                fileService.saveFileFromBase64String(fileName, epi.faceFileBase64Encoded)
//                epi.face = fileName
//                epi.faceFileBase64Encoded = ""
//            }
            println("updateEpi return -> 0 or 1")
            return epiRepo.updateEpi(epi)
        }catch (e : Exception){
            e.printStackTrace()
            //fileService.deleteFile(work.face)
            println("updateEpi return -> -100")
            return -100
        }
    }

    fun updateRelease(request: HttpServletRequest, epi : Epi) : Int{
        val usrId  = jwtService.getUsrIdByJWT(request)
        epi.usrId = usrId
        try {
            println("updateRelease return -> 0 or 1")
            return epiRepo.updateRelease(epi)
        }catch (e : Exception){
            e.printStackTrace()
            println("updateRelease return -> -100")
            return -100
        }
    }

    fun releasePlease(request: HttpServletRequest, epi : Epi) : Int{
        val usrId  = jwtService.getUsrIdByJWT(request)
        epi.usrId = usrId
        try {
             val result =epiRepo.releasePlease(epi)
             if(result == 1){
                 epiRepo.saveConfirm(epi)
                 return epi.releaseYn
             }
             else{
                 return 0
             }
        }catch (e : Exception){
            e.printStackTrace()
            println("releasePlease return -> -100")
            return -100
        }
    }

    fun epiConfirm(request: HttpServletRequest, epi : Epi) : Int{
        val usrId  = jwtService.getUsrIdByJWT(request)
        epi.usrId = usrId
        try {
            println("epiConfirm return -> 0 or 1")
            return epiRepo.epiConfirm(epi)
        }catch (e : Exception){
            e.printStackTrace()
            println("epiConfirm return -> -100")
            return -100
        }

    }

    fun saveEpi(request : HttpServletRequest, epi : Epi) : Any{
        val usrId = jwtService.getUsrIdByJWT(request)
        epi.usrId = usrId
        try {
            if(!(epi.face.equals(""))){
                val fileName = "${epi.workId}_${epi.epiId}"
                fileService.saveFileFromBase64String(fileName, epi.face,3)
                epi.face = fileName
            }
            transactionService.epiTrans(epi)
            println("saveEpi return -> $epi")
            return epi
        }catch (e : Exception){
            fileService.deleteFile(epi.face,3)
            e.printStackTrace()
            println("saveEpi return -> -100")
            return -100
        }
    }


}
