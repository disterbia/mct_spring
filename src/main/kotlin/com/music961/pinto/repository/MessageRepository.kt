package com.music961.pinto.repository

import com.music961.pinto.model.Message
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface MessageRepository {
    fun list(usrId :Int) : ArrayList<Message>
    fun send(usrId : Int , target : Int , text : String)
    fun read(messageId : Int)
}