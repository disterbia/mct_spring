package com.music961.pinto.model.work

import java.sql.Date
import java.sql.Timestamp

data class Opt(
        var workId      : Long   = 0,
        var epiId    : Long   = 0,
        var sceneId  : Long   = 0,
        var pageId   : Long   = 0,
        var optId    : Long   = 0,
        var protocol    : Int    = 0,
        var opt         : String = "",
        var usrId       : Int    = 0 //DB와 상관 X

)