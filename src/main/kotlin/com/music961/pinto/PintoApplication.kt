package com.music961.pinto

import com.music961.pinto.config.FileConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FileConfig::class)
class PintoApplication

fun main(args: Array<String>) {
	runApplication<PintoApplication>(*args)
}
