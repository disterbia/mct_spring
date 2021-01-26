package com.music961.pinto.model.work

import com.music961.pinto.model.work.Opt

data class Page(
        var workId      : Long   = 0,
        var epiId    : Long   = 0,
        var sceneId  : Long   = 0,
        var pageId   : Long   = 0,
        var opts        : ArrayList<Opt> = ArrayList<Opt>(), //컬럼과 상관X
        var usrId       : Int              = 0 //DB와 상관 X

)