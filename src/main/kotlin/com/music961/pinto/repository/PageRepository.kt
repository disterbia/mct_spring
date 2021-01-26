package com.music961.pinto.repository


import com.music961.pinto.model.work.Page
import com.music961.pinto.model.work.Scene
import com.music961.pinto.model.work.Work
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Mapper
interface PageRepository {

    fun getPageList(workId : Long?) : ArrayList<Page>
    fun getPageListAll(list : ArrayList<Work>) : ArrayList<Page>
    fun getPages(workId : Long , epiId : Long , sceneId : Long): ArrayList<Page>

    @Transactional
    fun createPage(page:Page) : Int
    @Transactional
    fun savePages(pages : ArrayList<Page>) : Int
    @Transactional
    fun deletePage(page : Page) : Int

}