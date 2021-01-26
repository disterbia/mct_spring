package com.music961.pinto.service

import com.music961.pinto.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService (val tagRepo : TagRepository){

    fun autoCompleteTag(tagValue : String) : Any{
        try {
            val result = tagRepo.autoCompleteTag(tagValue)
            println("autoCompleteTag return -> $result")
            return result
        }catch (e : Exception){
            e.printStackTrace()
            println("autoCompleteTag return -> -100")
            return -100
        }
    }
}
