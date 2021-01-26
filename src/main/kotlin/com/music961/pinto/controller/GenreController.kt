package com.music961.pinto.controller

import com.music961.pinto.service.GenreService
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/genre")
@Api(tags=["GenreContorller"])
class GenreController(val service : GenreService){

    @GetMapping("ac_genre/{genreValue}")
    @ApiOperation(value="장르 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : ArrayList\n-100 : DB에러"))
    fun autoCompleteGenre(@PathVariable @ApiParam("장르명",required = true) genreValue : String) : Any{
        println("genre/ac_genre/$genreValue")
        return service.autoCompleteGenre(genreValue)
    }
}