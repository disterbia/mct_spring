package com.music961.pinto.model

data class Packet(
        var code : Int, //100 성공 200 에러(임시)
        var token : String,
        var user : User?
)