package com.music961.pinto.model

import java.sql.Timestamp

data class EpiConfrim(
        var workId                   : Long             = 0,
        var epiId                    : Long             = 0,
        var confirmYn                : Int              = 0,
        var createDate               : Timestamp        = Timestamp(System.currentTimeMillis()),
        var updateDate               : Timestamp        = Timestamp(System.currentTimeMillis())
)