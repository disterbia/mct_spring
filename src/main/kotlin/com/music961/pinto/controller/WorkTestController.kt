package com.music961.pinto.controller

//import com.music961.pinto.model.work.*
//import com.music961.pinto.service.WorkTestService
//import io.swagger.annotations.Api
//import org.springframework.transaction.annotation.Transactional
//import org.springframework.web.bind.annotation.*
//import javax.servlet.http.HttpServletRequest
//
//@RestController
//@RequestMapping("/workt")
//class WorkTestController (val workTestService: WorkTestService){
//
//    /*
//    * JWT의 유효시간은 Interceptor 에서 캐치해서 처리하므로 이 컨트롤러에서는 사용자의 ID만 DB와 비교하여 검증!!
//    * */
//
//    @PostMapping("/updatework")
//    fun updateWork(
//            request : HttpServletRequest,
//            @RequestBody work : Work
//    ) : Any{
//        return workTestService.updateWork(request, work)
//    }
//
//    @PostMapping("creatework")
//    fun createWork(request : HttpServletRequest) : Any{
//        return workTestService.createWork(request)
//    }
//
//    @GetMapping("getepi")
//    fun getEpi() : Epi{
//        return workTestService.getEpi()
//    }
//
//    @PostMapping("saveepi")
//    fun saveEpi(@RequestBody epi : Epi) : Any{
//        return workTestService.saveEpi(epi)
//    }
//
//
//// Epi단위 Save 만들기
////=====================================================================================================
//
////    @GetMapping("getpage")
////    fun getPage() : Page{
////        return Page()
////    }
////
////    @PostMapping("savepage")
////    fun savePage(@RequestBody page : Page) : Any{
////        return workTestService.savePage(page)
////    }
////
////    @GetMapping("getscene")
////    fun getScene() : Scene{
////        return Scene()
////    }
////
////    @PostMapping("savescene")
////    fun saveScene(@RequestBody scene : Scene) : Any{
////        return workTestService.saveScene(scene)
////    }
////
////    @PostMapping("ftest")
////    fun ftest(@RequestBody genre: Genre) : String {
////        return workTestService.ftest(genre)
////    }
////
////    @GetMapping("test")
////    fun test() : Work{
////       return workTestService.test()
////    }
////
////    @GetMapping("gg")
////    fun gg() : Genre{
////        return Genre(0,0,"BL")
////    }
////
////    @PostMapping("gg2")
////    fun gg2(@RequestBody genre : Genre) : Genre{
////        return workTestService.gg2(genre)
////    }
////
////    @GetMapping("test2")
////    fun test2() : ArrayList<Var>{
////        return workTestService.test2()
////    }
////
////    @PostMapping("vartest")
////    fun varTest(@RequestBody v : Var) : Var{
////        return workTestService.varTest(v)
////    }
////
////    @PostMapping("/deletework2")
////    fun deleteWork2(
////            request : HttpServletRequest,
////            @RequestBody workId : String
////    ) : Int{
////        return workTestService.deleteWork2(request, workId)
////    }
////
////    @PostMapping("/savework")
////    fun saveWork(
////            request : HttpServletRequest,
////            @RequestBody work : Work
////    ) : Any{
////       return workTestService.saveWork(request, work)
////    }
//}