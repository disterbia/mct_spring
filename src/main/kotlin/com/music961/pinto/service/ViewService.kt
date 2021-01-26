package com.music961.pinto.service

import com.music961.pinto.repository.ViewRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ViewService (val viewRepo : ViewRepository){

    fun saveViewInfo(usrId : Int,id :Int){
        try {
            viewRepo.saveViewInfo(usrId,id)
        }catch (e : Exception){
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
}