package com.music961.pinto.model.work

import java.sql.Date
import java.sql.Timestamp

data class Var(
        var workId        : Long             = 0,
        var varId         : Long             = 0,
        var varType       : Int              = 0,
        var varValue      : String           = ""
)