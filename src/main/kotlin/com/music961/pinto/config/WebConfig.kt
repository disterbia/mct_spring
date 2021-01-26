package com.music961.pinto.config

import com.music961.pinto.service.LogService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class WebConfig(val logService: LogService) : WebMvcConfigurationSupport() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(createTokenByJwtInterceptor())
                .excludePathPatterns(
                        "/user/logintest",
                        "/user/login" ,
                        "/file/*/*",
                        "/file/*",
                        "/test/*",
                        "/admin/*",
                        "/workt/*",
                        "/genre/ac_genre/*",
                        "/work/releasebrabo",
                        "/swagger*/**",
                        "/static/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/tag/ac_tag/*",
                        "/res/search/*",
                        "/res/listobbrabo/*",
                        "/res/listobtime/*"
                        ) // JWT 검증 제외 리스트
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)
        registry.addMapping("/**")
    }

    @Bean
    fun createTokenByJwtInterceptor() : TokenByJwtInterceptor{
        return TokenByJwtInterceptor(logService)
    }


    override fun addResourceHandlers(registry:ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}