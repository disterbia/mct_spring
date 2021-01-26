package com.music961.pinto.config

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource
import com.zaxxer.hikari.HikariDataSource

@Configuration
@MapperScan(
        basePackages = ["com.music961.pinto.**.repository"],
        sqlSessionFactoryRef = "sessionFactory"
)
class DataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

        // UTF-8이 아닌 레거시 데이터베이스에 연결시 한글 문자열을 온전히 처리하기 위해 사용
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    @Primary
    @Bean
    fun sessionFactory(): SqlSessionFactory {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()

        sqlSessionFactoryBean.setDataSource((this.dataSource()))
        sqlSessionFactoryBean.setTypeAliasesPackage("com.music961.pinto")
        sqlSessionFactoryBean.setMapperLocations(PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"))
        sqlSessionFactoryBean.vfs = SpringBootVFS::class.java

        return sqlSessionFactoryBean.`object`!!
    }
}