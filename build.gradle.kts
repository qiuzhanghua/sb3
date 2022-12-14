import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.spring.dependency-management")
	id("org.springframework.boot")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
//	kotlin("kapt")
	id("com.gorylenko.gradle-git-properties")
//	id("io.spring.dependency-management")
//	application
//	id("com.github.johnrengelman.shadow")
}

group = "com.example"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

val mapstructVersion: String by project
val guavaVersion: String by project
val querydslVersion: String by project
val blazeVersion: String by project

// Customizing Tomcat version to 10.1.2 instead of 10.1.1
// extra["tomcat.version"] = "10.1.2"
// or defined in gradle.properties

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("com.google.guava:guava:${guavaVersion}")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.apache.commons:commons-collections4:4.4")

	implementation("com.blazebit:blaze-persistence-core-api-jakarta:${blazeVersion}")
	runtimeOnly("com.blazebit:blaze-persistence-core-impl-jakarta:${blazeVersion}")
	implementation("com.blazebit:blaze-persistence-entity-view-api-jakarta:${blazeVersion}")
	runtimeOnly("com.blazebit:blaze-persistence-entity-view-impl-jakarta:${blazeVersion}")
	runtimeOnly("com.blazebit:blaze-persistence-integration-hibernate-6.0:${blazeVersion}")
	compileOnly("com.blazebit:blaze-persistence-entity-view-processor:${blazeVersion}")
	implementation("com.blazebit:blaze-persistence-integration-entity-view-spring:${blazeVersion}")

//	implementation("com.blazebit:blaze-persistence-integration-querydsl-expressions-jakarta:${blazeVersion}")
	implementation("com.blazebit:blaze-persistence-integration-spring-data-2.7:${blazeVersion}")

	compileOnly("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	compileOnly("com.sun.xml.bind:jaxb-impl:4.0.1")
	compileOnly("jakarta.transaction:jakarta.transaction-api:2.0.1")
	compileOnly("jakarta.activation:jakarta.activation-api:2.1.0")
	compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
//	compileOnly("jakarta.persistence:jakarta.persistence-api:3.1.0")

//	implementation("com.querydsl:querydsl-jpa:${querydslVersion}:jakarta")
//	implementation("com.querydsl:querydsl-core:${querydslVersion}")
//	annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
//
//	// process Java
//	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
//	annotationProcessor("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
//	annotationProcessor("com.querydsl:querydsl-codegen:${querydslVersion}")

	// Use kotlin
//	kapt("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
//	kapt("com.querydsl:querydsl-kotlin-codegen:${querydslVersion}")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

springBoot {
	mainClass.set("com.example.sb3.App")
	buildInfo {
		properties {
			name.set("Learn Spring Boot 3")
		}
	}
}

// or

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	//mainClass.set("com.example.sb3.App")
	// or
	//manifest {
	//	attributes("Start-Class" to "com.example.sb3.App")
	//}

	launchScript()
	layered {
		enabled.set(true)
	}
}

//application {
//	mainClass.set("com.example.sb3.App")
//}
//
//tasks {
//	shadowJar {
//		minimize()
//	}
//}
