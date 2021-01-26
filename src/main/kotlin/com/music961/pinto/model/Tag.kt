package com.music961.pinto.model

import io.opencensus.tags.TagValue
import java.sql.Timestamp

data class Tag(
        var tagKey : Int = 0, //100 성공 200 에러(임시)
        var tagValue : String = "",
        var tagTime : Timestamp = Timestamp(System.currentTimeMillis())
)