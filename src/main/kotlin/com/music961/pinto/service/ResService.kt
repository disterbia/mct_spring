package com.music961.pinto.service

import com.music961.pinto.model.Res
import com.music961.pinto.model.ResUsr
import com.music961.pinto.model.Tag
import com.music961.pinto.repository.ResRepository
import com.music961.pinto.repository.TagRepository
import com.music961.pinto.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@Service
class ResService (val resRepo      : ResRepository,
                  val userRepo     : UserRepository,
                  val tagRepo      : TagRepository,
                  val jwtService   : MyJwtService,
                  val fileService  : FileService,
                  val logService   : LogService){

    fun getMyLikeList(request: HttpServletRequest) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val resListBrabo : ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            resListBrabo = resRepo.getMyLikeList(id)
            resListBrabo.forEach {
                val tags = tagRepo.getAllTagByRes(it.fileName)
                if (tags.size > 0) {
                    var tagString = ""
                    tags.forEach { t ->
                        tagString = "${tagString}#${t.tagValue}"
                    }
                    it.tag = tagString
                }

                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    resList.add(res)
                } else if(it.resGroup ==0L) {
                    val result= resRepo.getLikeResByResGroup(id, it.fileName)
                    if(result.isNotEmpty()){
                        resList.add(result)
                    }
                }
            }
            println("getMyLikeList return -> $resList")
            return resList
        }catch (e : Exception){
            e.printStackTrace()
            println("getMyLikeList return -> -100")
            return -100
        }
    }
///////
    fun listOrderByBrabo(request: HttpServletRequest , cat : String ,price : String) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val resListBrabo : ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            if (price == "0") {
                resListBrabo = resRepo.getAllListOrderByBrabo0(cat.toInt(), id)
            } else if (price == "1") {
                resListBrabo = resRepo.getAllListOrderByBrabo1(cat.toInt(), id)
            } else if (price == "2") {
                resListBrabo = resRepo.getAllListOrderByBrabo2(cat.toInt(), id)
            } else return -200
            run {
            resListBrabo.forEach {
                val tags = tagRepo.getAllTagByRes(it.fileName)
                if (tags.size > 0) {
                    var tagString = ""
                    tags.forEach { t ->
                        tagString = "${tagString}#${t.tagValue}"
                    }
                    it.tag = tagString
                }

                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    resList.add(res)
                } else if(it.resGroup ==0L) {
                    val result= resRepo.getResByResGroup(cat.toInt(), id, it.fileName)
                    if(result.isNotEmpty()){
                        resList.add(result)
                    }
                }

                if (resList.size == 8) return@run
            }

        }
            println("listOrderByBrabo return -> $resList")
            return resList
        }catch (e : Exception){
            e.printStackTrace()
            println("listOrderByBrabo return -> -100")
            return -100
        }

    }
    fun listOrderByBrabo(request: HttpServletRequest , cat : String, price : String ,fn : String ) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val resListBrabo : ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            if(fn == "0") return listOrderByBrabo(request,cat,price)
            if(price == "0") {
                resListBrabo = resRepo.getListOrderByBrabo0(cat.toInt(),id,fn.toInt())
            }else if(price == "1"){
                resListBrabo = resRepo.getListOrderByBrabo1(cat.toInt(),id,fn.toInt())
            }else if(price == "2"){
                resListBrabo = resRepo.getListOrderByBrabo2(cat.toInt(),id,fn.toInt())
            }
            else return -200
            run {
                resListBrabo.forEach {
                    val tags = tagRepo.getAllTagByRes(it.fileName)
                    if (tags.size > 0) {
                        var tagString = ""
                        tags.forEach { t ->
                            tagString = "${tagString}#${t.tagValue}"
                        }
                        it.tag = tagString
                    }

                    if (it.resGroupCount == 0 && it.resGroup ==0L) {
                        val res = arrayListOf<Res>(it)
                        resList.add(res)
                    } else if(it.resGroup ==0L) {
                        val result= resRepo.getResByResGroup(cat.toInt(), id, it.fileName)
                        if(result.isNotEmpty()){
                            resList.add(result)
                        }
                    }

                    if (resList.size == 8) return@run
                }
            }
            println("listOrderByBrabo return -> $resList")
            return resList
        }catch (e : Exception) {
            e.printStackTrace()
            println("listOrderByBrabo return -> -100")
            return -100
        }
    }

    fun listOrderByTime(request: HttpServletRequest, cat : String, price : String) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val resListTime : ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            if(price == "0") {
                resListTime = resRepo.getAllListOrderByTime0(cat.toInt(),id)
            }else if(price == "1"){
                resListTime = resRepo.getAllListOrderByTime1(cat.toInt(),id)
            }else if(price == "2"){
                resListTime = resRepo.getAllListOrderByTime2(cat.toInt(),id)
            }
            else return -200
            run {
                resListTime.forEach {
                    val tags = tagRepo.getAllTagByRes(it.fileName)

                    if (tags.size > 0) {
                        var tagString = ""
                        tags.forEach { t ->
                            tagString = "${tagString}#${t.tagValue}"
                        }
                        it.tag = tagString
                    }

                    if (it.resGroupCount == 0 && it.resGroup ==0L) {
                        val res = arrayListOf<Res>(it)
                        resList.add(res)
                    } else if(it.resGroup ==0L){
                        val result= resRepo.getResByResGroup(cat.toInt(), id, it.fileName)
                        if(result.isNotEmpty()){
                            resList.add(result)
                        }
                    }
                    if (resList.size == 8) return@run
                }
            }
            println("listOrderByTime return -> $resList")
            return resList
        }catch (e : Exception){
            e.printStackTrace()
            println("listOrderByTime return -> -100")
            return -100
        }
    }

    fun listOrderByTime(request: HttpServletRequest, cat : String, price : String ,fn : String) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val resListTime: ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            if (fn == "0") return listOrderByTime(request, cat, price)
            if (price == "0") {
                resListTime = resRepo.getListOrderByTime0(cat.toInt(), id, fn.toInt())
            } else if (price == "1") {
                resListTime = resRepo.getListOrderByTime1(cat.toInt(), id, fn.toInt())
            } else if (price == "2") {
                resListTime = resRepo.getListOrderByTime2(cat.toInt(), id, fn.toInt())
            } else return -200
            run{
            resListTime.forEach {
                val tags = tagRepo.getAllTagByRes(it.fileName)
                if (tags.size > 0) {
                    var tagString = ""
                    tags.forEach { t ->
                        tagString = "${tagString}#${t.tagValue}"
                    }
                    it.tag = tagString
                }

                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    resList.add(res)
                } else if(it.resGroup ==0L){
                    val result= resRepo.getResByResGroup(cat.toInt(), id, it.fileName)
                    if(result.isNotEmpty()){
                        resList.add(result)
                    }
                }
                if (resList.size == 8) return@run
            }
        }
            println("listOrderByTime return -> $resList")
            return resList
        }catch (e : Exception){
            e.printStackTrace()
            println("listOrderByTime return -> -100")
            return -100
        }
    }

    fun resSearch(request: HttpServletRequest,text : String) : Any {
        val id = jwtService.getUsrIdByJWT(request)
        val searchRes: ArrayList<Res>
        val resList = mutableSetOf<ArrayList<Res>>()
        try {
            searchRes = resRepo.resSearch(id,text)

            searchRes.forEach {
                val tags = tagRepo.getAllTagByRes(it.fileName)
                if (tags.size > 0) {
                    var tagString = ""
                    tags.forEach { t ->
                        tagString = "${tagString}#${t.tagValue}"
                    }
                    it.tag = tagString
                }

                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    resList.add(res)
                } else if(it.resGroup ==0L){
                    val result= resRepo.searchResByResGroup(id, it.fileName)
                    if(result.isNotEmpty()){
                        resList.add(result)
                    }
                }
            }
            println("resSearch return -> $resList")
            return resList
        }catch (e : Exception){
            e.printStackTrace()
            println("resSearch return -> -200")
            return -200
        }
    }

    fun buyList(request: HttpServletRequest) : Any {
        val id = jwtService.getUsrIdByJWT(request)
        val buyRes : ArrayList<Res>
        val buyList = mutableSetOf<ArrayList<Res>>()
        try {
            buyRes = resRepo.buyList(id)

            buyRes.forEach {
                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    buyList.add(res)
                } else if(it.resGroup ==0L){
                    val result= resRepo.getResUsrByResGroup(id, it.fileName)
                    if(result.isNotEmpty()){
                        buyList.add(result)
                    }
                }
            }
            println("buyList return -> $buyList")
            return buyList
        }catch (e :Exception){
            e.printStackTrace()
            println("buyList return -> -200")
            return -200
        }
    }

    fun soldList(request: HttpServletRequest) : Any {
        val id = jwtService.getUsrIdByJWT(request)
        val soldRes : ArrayList<Res>
        val soldList = mutableSetOf<ArrayList<Res>>()
        try {
            soldRes=resRepo.soldList(id)

            soldRes.forEach {
                if (it.resGroupCount == 0 && it.resGroup ==0L) {
                    val res = arrayListOf<Res>(it)
                    soldList.add(res)
                } else if(it.resGroup == 0L) {
                    val result= resRepo.getResUsrByResGroup(id, it.fileName)
                    if(result.isNotEmpty()){
                        soldList.add(result)
                    }
                }

            }
            println("soldList return -> $soldList")
            return soldList
        }catch (e :Exception){
            e.printStackTrace()
            println("soldList return -> -200")
            return -200
        }
    }

    fun list(request : HttpServletRequest, reqBody: HashMap<String,ArrayList<Int>>?) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        lateinit var cFileList : ArrayList<Int>

        if(reqBody?.get("files") != null) {
            cFileList = reqBody.get("files")!!
            if(cFileList.size == 0){
                cFileList.add(0)
            }
        }else {
            cFileList = ArrayList<Int>()
            cFileList.add(0)
        }

        lateinit var fileNames :ArrayList<Int>

        fileNames = resRepo.getFileList(id, cFileList)


        var fileTotalSize : Long = 0
        fileNames.forEach { i ->
            fileTotalSize += fileService.getFileSize(i.toString())
        }

        val fileSizeAndFileNames = LinkedHashMap<String, Any>()
        fileSizeAndFileNames["fullSize"] = fileTotalSize
        fileSizeAndFileNames["files"] = fileNames

        println("list return -> $fileSizeAndFileNames")
        return fileSizeAndFileNames
    }

    fun list(request : HttpServletRequest, fileName : Int) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        val res = resRepo.getResource(id,fileName)
        if(res == null){
            println("list return -> -2")
            return -2
        }//res.fileName.toString()
        val file = fileService.loadFileToBase64String(fileName.toString(),0)
        if(file == -404 || file == -100)return file

        res.file = file as String

        println("list return -> $res")
        return res
    }
    fun list(fileName : Int) : Any{
        val res = resRepo.getMusic(fileName)
        if(res == null){
            println("list return -> -2")
            return -2
        }//res.fileName.toString()
        val file = fileService.loadFileToBase64String(fileName.toString(),0)
        if(file == -200 || file == -100)return file

        res.file = file as String

        println("list return -> ${res.file}")
        return res.file
    }

    fun uploadChar(request: HttpServletRequest, resArr : ArrayList<Res>) : Int{
        val id = jwtService.getUsrIdByJWT(request)
        val nowTime = LocalDateTime.now()
        var time = nowTime.toString().replace("-","")
        time = time.replace("T","")
        time = time.replace(":","")
        time = time.substring(0,14)
        time = "${time}_${id}"

        resArr.forEach { res->
            val file = res.file
            res.file = ""
            res.usr = id
            res.gr = time
            try {
                println(res.cat)
                println(res.usr)
                println(res.title)
                println(res.price)
                println(res.priceDis)
                println(res.note)
                println(res.resGroup)
                resRepo.saveFile(res)

                fileService.saveFileFromBase64String(res.fileName.toString(), file,0) //cat - 0:res 1:profile
                fileService.saveFileFromBase64String(res.fileName.toString(), file,0) //cat - 0:res 1:profile

                fileService.saveFileFromBase64String(res.fileName.toString() + "_origin", res.fileOrigin,0)
                fileService.saveFileFromBase64String(res.fileName.toString() + "_thumb", res.fileThumbnail,0)

                if(res.tag.length > 0){
                    val tagList = res.tag.split("#")
                    for(i in 1..tagList.size-1){

                        val tagStr = tagList.get(i)
                        println(tagStr)
                        val tag = tagRepo.getTagByTagName(tagStr)
                        if(tag == null || tag.tagValue == ""){
                            //tag 테이블 넣고시작
                            val t = Tag(0,tagStr)
                            tagRepo.insertTag(t)
                            tagRepo.insertResTag(res.fileName, t.tagKey)
                        }else{
                            //tag 의 tag_key를 f_res_tag에 같이 삽입
                            tagRepo.insertResTag(res.fileName, tag.tagKey)
                        }
                    }
                }

            }catch (e : Exception){
                e.printStackTrace()
                println("uploadChar return -> -100")
                return -100
            }
        }
        println("uploadChar return -> 100")
        return 100

    }

    fun upload(request : HttpServletRequest, res : Res) : Int {
        val id = jwtService.getUsrIdByJWT(request)
        val file = res.file
        res.file = ""
        res.usr = id

        try {
            resRepo.saveFile(res)
            try {
                fileService.saveFileFromBase64String(res.fileName.toString(), file,0)
                fileService.saveFileFromBase64String(res.fileName.toString(), file,0)
                /*
                 * 3  character
                 * 10 background
                 * 25 music
                 */
                if(res.cat != 25){
                    fileService.saveFileFromBase64String(res.fileName.toString() + "_origin", res.fileOrigin,0)
                    fileService.saveFileFromBase64String(res.fileName.toString() + "_thumb", res.fileThumbnail,1)
                }

                if(res.tag.length > 0){
                    val tagList = res.tag.split("#")
                    for(i in 1..tagList.size-1){

                        val tagStr = tagList.get(i)
                        println(tagStr)
                        val tag = tagRepo.getTagByTagName(tagStr)
                        if(tag == null || tag.tagValue == ""){
                            //tag 테이블 넣고시작
                            val t = Tag(0,tagStr)
                            tagRepo.insertTag(t)
                            tagRepo.insertResTag(res.fileName, t.tagKey)
                        }else{
                            //tag 의 tag_key를 f_res_tag에 같이 삽입
                            tagRepo.insertResTag(res.fileName, tag.tagKey)
                        }
                    }
                }
                println("upload return -> 100")
                return 100
            }catch (e:Exception){
                e.printStackTrace()
                println("upload return -> -200")
                return -200
            }
        }catch (e:Exception){
            e.printStackTrace()
            println("return -> -100")
            return -100
        }
    }

    fun buy(request : HttpServletRequest, resId : Int) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        try {
            val gem = resRepo.getGemPrice(id, resId)
            if(gem.size == 0){
                // user정보나 res정보가 DB에 존재하지 않음
                logService.logError(request,"-404")
                println("buy return -> -2")
                return -2
            }

            val userGem  = gem.get("GEM")!!
            val resPrice = gem.get("PRICE")!!
            val resDis   = gem.get("DIS")
            var price    = resPrice
            if(resDis!! > 0){
                price = price * (100 - resDis.toInt())/100
            }
            if(userGem < price){
                logService.logError(request,"-20000")
                println("buy return -> -200")
                return -200
            }

            // 여기서 부터 DB Insert 및 업데이트
            // 1. f_res_usr insert(여기서 에러나면 PK 중복 방지 에러도 포함이라 중복구매 예방!), userGem updat
            buy(id,resId.toLong(),price)
            println("구매완료")
            // 2. user객체 + file return
            try {
                val updatedUser = userRepo.getUserById(id)
                val boughtRes = resRepo.getResource(id, resId)!!
                boughtRes.file = fileService.loadFileToBase64String(resId.toString(),0) as String
                println("업로드 완료")

                val returnVal = HashMap<String, Any?>()
                returnVal["resource"] = boughtRes
                returnVal["user"] = updatedUser

                logService.logError(request,"resource = ${returnVal["resource"]} user = ${returnVal["user"]}")
            }catch (e:Exception){
                e.printStackTrace()
                return -300
            }

            println("buy return -> 100")
            return 100
        }catch (e : Exception){
            logService.logError(request,e)
            e.printStackTrace()
            println("buy return -> -100")
            return -100
        }
    }

    @Transactional
    fun buy(id : Int, res : Long, price : Int){
        println(id)
        try{
            resRepo.buyStep1(id,res)
            resRepo.buyStep2(id,price)
        }catch (e : Exception){
            throw RuntimeException(e)
        }
    }

    fun saveBrabo(request: HttpServletRequest , resId: Int) : Any{
        val id = jwtService.getUsrIdByJWT(request)
        try{
            resRepo.saveBrabo(id,resId)
            println("saveBrabo return -> 100")
            return 100
        }catch (e: Exception) {
            e.printStackTrace()
            println("saveBrabo return -> -100")
            return -100
        }
    }

    fun deleteBrabo(request: HttpServletRequest,resId : Int): Any{
        val id = jwtService.getUsrIdByJWT(request)
        try {
            resRepo.deleteBrabo(id,resId)
            println("deleteBrabo return -> 100")
            return 100
        }catch (e : Exception){
            e.printStackTrace()
            println("deleteBrabo return -> -100")
            return -100
        }
    }

}