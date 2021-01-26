import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.music961"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}




dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-tomcat") //톰캣
	implementation("org.springframework.boot:spring-boot-starter-web") //웹
	implementation("org.springframework.boot:spring-boot-starter-jdbc") // DB연결을 위한 JDBC
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2") // Mybatis
	implementation("org.mariadb.jdbc:mariadb-java-client:2.1.2") //mariadb
	implementation("com.google.firebase:firebase-admin:6.8.1") //Google FireBase OAuth
	implementation("com.google.gms:google-services:3.0.0")
	implementation("io.jsonwebtoken:jjwt:0.7.0") //JJWT
	implementation("javax.xml.bind:jaxb-api:2.3.1") //JAXB(JJWT 암호화관련)
	implementation("org.springframework.boot:spring-boot-devtools:2.3.0.RELEASE") //Hot Reroad
	implementation( group = "org.slf4j", name = "jcl-over-slf4j", version = "1.7.25")
	implementation( group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")

	implementation(group = "io.springfox", name= "springfox-swagger2", version= "2.9.2"){
		exclude("io.swagger:swagger-models")
	}
	implementation(group = "io.springfox", name= "springfox-swagger-ui", version= "2.9.2")
	implementation("io.swagger:swagger-models:1.5.21")


	//implementation("org.springframework.boot:spring-boot-starter-oauth2-client") //OAuth
	//implementation("org.springframework.boot:spring-boot-starter-security") //시큐리티



	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
