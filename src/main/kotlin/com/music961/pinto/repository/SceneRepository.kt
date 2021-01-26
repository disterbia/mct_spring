package com.music961.pinto.repository

import com.music961.pinto.model.work.Scene
import com.music961.pinto.model.work.Work
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Mapper
interface SceneRepository{
    fun getSceneList(workId : Long?) : ArrayList<Scene>
    fun getSceneListAll(list : ArrayList<Work>) : ArrayList<Scene>
    fun getMyScene(workId : Long, epiId : Long) : ArrayList<Scene>
    @Transactional
    fun saveSceneWaterFall(scene:Scene): Int
    @Transactional
    fun saveScenes(scenes : ArrayList<Scene>) : Int
    fun createScene(scene : Scene) : Int
    fun updateScene(scene: Scene) : Int
    fun deleteScene(scene: Scene) : Int
}