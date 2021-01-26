package com.music961.pinto.model

import java.sql.Date
import java.sql.Timestamp

data class Res(
        var fileName : Long = 0, //파일명과 연결
        var cat      : Int = 0, //카테고리
        var usr      : Int = 0,    //생성한 유저의 key
        var title    : String = "",
        var date     : Timestamp = Timestamp(System.currentTimeMillis()),
        var price    : Int? =0,
        var priceDis : Int?=0,
        var note     : String?="",
        var view     : Int?=0,    //조회수
        var file     : String = "", // Base64 Encoding 된 File //DB와상관X
        var fileThumbnail : String = "",                      //DB와상관X
        var fileOrigin : String = "",                         //DB와상관X
        var tag : String = "",                                     //DB와상관X
        var mybrabo : Int? = 0, //조인해서가져옴
        var brabocnt : Int? = 0, //조인해서가져옴
        var gr : String = "0",
        var resGroup : Long = 0,
        var resGroupCount : Int = 0,
        var openYn : Int = 0,
        var usrName : String = ""
        /*
         * 3  character
         * 5  소품
         * 10 background
         * 25 music
         */
)