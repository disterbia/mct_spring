package com.music961.pinto.model

import java.sql.Timestamp

data class Message(
        var id           : Int       = 0 ,
        var toUsrId      : Long      = 0,
        var fromUsrId    : Long      = 0,
        var MessageValue : String    = "",
        var checked      : Int       = 0,
        var sendDate     : Timestamp = Timestamp(System.currentTimeMillis())
)