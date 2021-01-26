package com.music961.pinto.model.work

data class Cast(
        var workId  : Long = 0 ,
        var varId   : Long = 0 ,
        var usrId   : Int  = 0 ,
        var castId  : Int  = 0 ,
        var resNo  : Long = 0 ,
        var name    : String = "",
        var zoom    : Double = 0.0 ,
        var biasX   : Double = 0.0 ,
        var biasY   : Double = 0.0
)