package com.music961.pinto.controller

import com.music961.pinto.model.Res
import com.music961.pinto.service.ResService
import io.swagger.annotations.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@RestController
@RequestMapping("/res")
@Api(tags=["ResContorller"])
class ResController (val resService: ResService){
    /*
    * JWT의 유효시간은 Interceptor 에서 캐치해서 처리하므로 이 컨트롤러에서는 사용자의 ID만 DB와 비교하여 검증!!
    * */


    @GetMapping("like")
    @ApiOperation(value="나의 좋아요 리스트", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러"))
    fun getMyLikeList(request: HttpServletRequest) : Any{
        println("res/like")
        return resService.getMyLikeList(request)
    }
    @GetMapping("listobbrabo/{cat}/{price}")
    @ApiOperation(value="좋아요순 Res 전체 리스트")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n" +
            "-200 : 잘못된 요청"))
    fun listOrderByBrabo(
            request: HttpServletRequest,
            @PathVariable @ApiParam("카테고리번호",required = true) cat : String,
            @PathVariable  @ApiParam("분류: 0 = 전체 1 = 무료 2 = 유료",required = true)price : String

    ) : Any {
        println("res/listobbrabo/${cat}/${price}")
        return resService.listOrderByBrabo(request,cat,price)
    }
    @GetMapping("listobbrabo/{cat}/{price}/{fn}")
    @ApiOperation(value="좋아요순 Res 20개 리스트")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n-200 : 잘못된 요청"))
    fun listOrderByBrabo(
            request: HttpServletRequest,
            @PathVariable  @ApiParam("카테고리번호",required = true) cat : String,
            @PathVariable  @ApiParam("분류: 0 = 전체 1 = 무료 2 = 유료",required = true)price : String,
            @PathVariable  @ApiParam("마지막Res키",required = true)fn : String

    ) : Any {
        println("res/listobbrabo/${cat}/${price}/${fn}")
       return resService.listOrderByBrabo(request,cat,price,fn)
    }

    @GetMapping("listobtime/{cat}/{price}")
    @ApiOperation(value="최신순 Res 전체 리스트")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n" +
            "-200 : 잘못된 요청"))
    fun listOrderByTime(
            request: HttpServletRequest,
            @PathVariable  @ApiParam("카테고리번호",required = true) cat : String,
            @PathVariable  @ApiParam("분류: 0 = 전체 1 = 무료 2 = 유료",required = true)price : String
    ) : Any {
        println("res/listobtime/${cat}/${price}")
        return resService.listOrderByTime(request, cat,price)
    }

    @GetMapping("listobtime/{cat}/{price}/{fn}")
    @ApiOperation(value="최신순 Res 20개 리스트")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-100 : DB에러\n-200 : 잘못된 요청"))
    fun listOrderByTime(
            request: HttpServletRequest,
            @PathVariable  @ApiParam("카테고리번호",required = true) cat : String,
            @PathVariable  @ApiParam("분류: 0 = 전체 1 = 무료 2 = 유료",required = true)price : String,
            @PathVariable  @ApiParam("첫번째Res키",required = true) fn : String
    ) : Any {
        println("res/listobtime/${cat}/${price}/${fn}")
       return resService.listOrderByTime(request, cat,price,fn)
    }

    @GetMapping("/download/{fileName}")
    @ApiOperation(value="파일 담아서 Res가져오기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-2:해당파일없음"))
    fun list(
            request : HttpServletRequest,
            @PathVariable @ApiParam("파일번호",required = true) fileName : Int
    ) : Any{
        println("res/download/${fileName}")
       return resService.list(request, fileName)
    }

    @GetMapping("/music/{fileName}")
    @ApiOperation(value="음악 파일 가져오기")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : Base64String\n-2 : DB파일없음\n" +
            "-200 : 서버에파일없음\n-100 : 파일로딩에러"))
    fun list(
            @PathVariable @ApiParam(value = "파일번호",required = true) fileName : Int
    ) : Any{
        println("res/music/${fileName}")
        return resService.list(fileName)
    }

    @GetMapping("/search/{text}")
    @ApiOperation(value="RES검색" ,notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-200:DB에러"))
    fun resSearch(request: HttpServletRequest,@PathVariable text : String) : Any{
        println("res/search/${text}")
        return resService.resSearch(request,text)
    }

    @GetMapping("/buylist")
    @ApiOperation(value="구매내역",notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-200:DB에러"))
    fun buyList(request: HttpServletRequest) : Any{
        println("res/buylist")
        return resService.buyList(request)
    }

    @GetMapping("/soldlist")
    @ApiOperation(value="판매내역",notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : JSON\n-200:DB에러"))
    fun soldList(request: HttpServletRequest) : Any{
        println("res/soldlist")
        return resService.soldList(request)
    }

    // 완료
    @PostMapping("/list")
    @ApiOperation(value="파일 이름과 크기 가져오기", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : LinkedHashMap"))
    fun list(
            request : HttpServletRequest,
            @RequestBody  @ApiParam("files:[파일번호들]",required = true) reqBody: HashMap<String,ArrayList<Int>>?
            ) : Any{
        println("res/list")
       return resService.list(request, reqBody)
    }

    @PostMapping("/uploadchar")
    @ApiOperation(value="Res 여러개 업로드", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러\n"))
    fun uploadChar(
        request: HttpServletRequest,
        @RequestBody @ApiParam(value = "RES리스트",required = true) resArr : ArrayList<Res>
    ) : Int{
        println("res/uploadchar")
       return resService.uploadChar(request,resArr)
    }

    @PostMapping("/upload")
    @ApiOperation(value="Res 업로드", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-100 : DB에러(파일)\n" +
            "-200 : DB에러(태그)"))
    fun upload(
            request : HttpServletRequest,
            @RequestBody @ApiParam(value = "RES객체",required = true) res : Res
    ) : Int {
        println("res/upload")
        return resService.upload(request,res)
    }

    @PostMapping("/buy/{resId}")
    @ApiOperation(value="Res 구매", notes="Request Header : JWT TOKEN")
    @ApiResponses(ApiResponse(code = 200, message = "ResponseBody : 100\n-2 : 해당파일없음\n" +
            "-200 : 금액부족\n-100:DB에러(트랜잭션 실패)\n-300 : 파일없음"))
    fun buy(
            request : HttpServletRequest,
            @PathVariable @ApiParam(value = "RES번호",required = true) resId : Int
    ) : Any{
        println("res/buy/${resId}")
        return resService.buy(request,resId)
    }
}