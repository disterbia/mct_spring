package com.music961.pinto.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate

@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //기본정보
                .select()
                .apis(RequestHandlerSelectors.any()) //주소접근 옵션
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, getArrayList())
//                .globalResponseMessage(RequestMethod.POST, getArrayList())


    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("PINTO API 명세서")
                .description("${LocalDate.now()} 맥스")
                .version("1.0")
                .build()
    }

//    private fun getArrayList(): ArrayList<ResponseMessage>? {
//        val lists = ArrayList<ResponseMessage>()
//        lists.add(ResponseMessageBuilder().code(-200).message("서버에러").responseModel(ModelRef("Error")).build())
//        lists.add(ResponseMessageBuilder().code( 200).message("성공").responseModel(ModelRef("OK")).build())
//        return lists
//    }
//    private fun postArrayList(): ArrayList<ResponseMessage>? {
//        val lists = ArrayList<ResponseMessage>()
//        lists.add(ResponseMessageBuilder().code(-200).message("서버에러").responseModel(ModelRef("Error")).build())
//        lists.add(ResponseMessageBuilder().code( 200).message("성공").responseModel(ModelRef("OK")).build())
//        return lists
//    }

}