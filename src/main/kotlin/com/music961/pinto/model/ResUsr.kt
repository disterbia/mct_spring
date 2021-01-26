package com.music961.pinto.model

import java.sql.Timestamp

data class ResUsr(
        var usrKey     : Int = 0,
        var resKey     : Long = 0,
        var gem        : Int = 0,
        var createDate : Timestamp = Timestamp(System.currentTimeMillis()),
        var resGroup : String = "",
        var resGroupCount : Int = 0
)