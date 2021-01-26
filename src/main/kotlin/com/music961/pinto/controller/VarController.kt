package com.music961.pinto.controller

import com.music961.pinto.service.VarService
import io.swagger.annotations.Api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/var")
@Api(tags=["ResContorller"])
class VarController (val service : VarService){

}