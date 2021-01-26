package com.music961.pinto.service

import com.music961.pinto.model.Res
import com.music961.pinto.model.Tag
import com.music961.pinto.repository.ResRepository
import com.music961.pinto.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AdminService(val fileService : FileService,
                   val resRepo     : ResRepository,
                   val tagRepo     : TagRepository) {
    fun musicUpload(
            name  : String,
            pnote  : String,
            pprice : String,
            tags  : String,
            pass  : String,
            user  : String,
            file  : MultipartFile
    ): String{

        val res = Res().apply {
            title = name
            note = pnote
            price = pprice.toInt()
            usr = user.toInt()
            tag = tags
            cat = 25
        }


        try {
            resRepo.saveFile(res)
            fileService.saveFile(res.fileName.toString(), file)

            if(res.tag.length > 0){
                val tagList = res.tag.split("#")
                for(i in 1..tagList.size-1){
                    val tagStr = tagList.get(i)
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
            println("musicUpload return -> ${e.message.toString()}")
            return e.message.toString()
        }
        println("musicUpload return -> filename : ${res.fileName}\n" +
                "filesize : ${file.size}")
        return "filename : ${res.fileName}\nfilesize : ${file.size}"
    }
}