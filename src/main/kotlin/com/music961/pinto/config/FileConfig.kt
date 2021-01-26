package com.music961.pinto.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix="file")
class FileConfig {
    lateinit var dir : String
}