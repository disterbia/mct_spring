package com.music961.pinto.model.work

import java.sql.Timestamp

data class Epi(
        var workId                   : Long             = 0,
        var epiId                    : Long             = 0,
        var title                    : String           = "",
        var face                     : String           = "",
        var releaseYn                : Int              = 0,
        var scenes                   : ArrayList<Scene> = ArrayList<Scene>(), //컬럼과 상관X
        var usrId                    : Int              = 0, //DB와 상관 X
        var faceFileBase64Encoded    : String           = "",
        var createDate               : Timestamp        = Timestamp(System.currentTimeMillis()),
        var updateDate               : Timestamp        = Timestamp(System.currentTimeMillis())
)