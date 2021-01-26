package com.music961.pinto.model.work

import com.music961.pinto.model.work.Page

data class Scene(
        var workId      : Long   = 0,
        var epiId    : Long   = 0,
        var sceneId  : Long   = 0,
        var name        : String  = "",
        var lastedit    : Int = 0,
        var bfScene     : Long = 0,
        var bfPage      : Long = 0,
        var note        : String = "",
        var pages       : ArrayList<Page> = ArrayList<Page>(), //컬럼과 상관X
        var usrId       : Int              = 0 //DB와 상관 X

)