package com.music961.pinto.model.work

import java.sql.Date
import java.sql.Timestamp

data class Genre(
        var workId      : Long             = 0,
        var genreId     : Long             = 0,
        var genreValue  : String           = ""
)