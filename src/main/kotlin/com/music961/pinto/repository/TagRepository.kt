package com.music961.pinto.repository

import com.music961.pinto.model.Tag
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface TagRepository {
    fun getAllTagByRes(res : Long) : ArrayList<Tag>
    fun getTagByTagName(tag : String) : Tag?
    fun autoCompleteTag(tagValue : String) : ArrayList<String>
    fun insertTag(tag : Tag) : Int
    fun insertResTag(res : Long, tag : Int)
    fun removeAllResTagByRes(res : Long)
    fun removeResTag(res : Long, tag : String)
}