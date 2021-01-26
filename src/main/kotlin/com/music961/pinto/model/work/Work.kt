package com.music961.pinto.model.work

import com.music961.pinto.model.work.Epi
import com.music961.pinto.model.work.Genre
import com.music961.pinto.model.work.Var
import java.sql.Timestamp

data class Work(
        var id                       : Long             = 0,
        var title                    : String           = "",
        var lastedit                 : Int              = 0,
        var face                     : Long             = 0,
        var usrKey                   : Int              = 0,
        var vars                     : ArrayList<Var>   = ArrayList<Var>(), //컬럼과 상관X 조인해서삽입
        var genres                   : ArrayList<Genre> = ArrayList<Genre>(), //컬럼과 상관X 조인해서삽입
        var epis                     : ArrayList<Epi>   = ArrayList<Epi>(), //컬럼과 상관X
        var casts                    : ArrayList<Cast>  = ArrayList<Cast>(), //컬럼과 상관X
        var faceFileBase64Encoded    : String           = "",
        var lang                     : String           = "",
        var bCount                   : Int              = 0, // 칼럼과 상관X 조인해서 삽입
        var braboYn                  : Int              = 0, // 칼럼과 상관X 조인해서 삽입
        var usrName                  : String           = "",
        var viewCount                : Int              = 0, // 칼럼과 상관X 조인해서 삽입
        var note                     : String           = "",
        var genreId                  : Long             = 0,
        var genreValue               : String           = "",
        var epiId                    : Long             = 0,
        var sceneId                  : Long             = 0,
        var pageId                   : Long             = 0,
        var readDate                 : Timestamp        = Timestamp(System.currentTimeMillis()),
        var endYn                    : Int              = 0,
        var temp                     : Int              = 0
)