package com.music961.pinto.controller

import com.music961.pinto.service.TagService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tag")
@Api(tags=["TagContorller"])
class TagController(val service : TagService){

    @GetMapping("ac_tag/{tagValue}")
    @ApiOperation(value="태그 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ArrayList\n-100 : DB에러"))
    fun autoCompleteGenre(@PathVariable @ApiParam("태그명",required = true) tagValue : String) : Any{
        println("tag/ac_tag/${tagValue}")
        return service.autoCompleteTag(tagValue)
    }
}