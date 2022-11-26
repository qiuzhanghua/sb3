import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.spring.dependency-management")
	id("org.springframework.boot")
	kotlin("jvm")
	kotlin("plugin.spring")
	id("com.gorylenko.gradle-git-properties")
//	id("io.spring.dependency-management")
//	application
//	id("com.github.johnrengelman.shadow")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
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

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
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
	implementation("org.mapstruct:mapstruct:${mapstructVersion}")
}

tasks.withType<JavaCompile> {
	options.compilerArgs.addAll(
		listOf(
			"-Amapstruct.suppressGeneratorTimestamp=true",
			"-Amapstruct.suppressGeneratorVersionInfoComment=true",
			"-Amapstruct.defaultComponentModel=spring"
		)
	)
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
