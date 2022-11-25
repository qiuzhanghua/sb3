rootProject.name = "sb3"

pluginManagement {
    val kotlinVersion: String by settings
    val shadowVersion: String by settings
    val bootVersion: String by settings
    val depVersion: String by settings
    val gitPropVersion: String by settings

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("org.springframework.boot") version bootVersion
        id("io.spring.dependency-management") version depVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("com.github.johnrengelman.shadow") version shadowVersion
        id("com.gorylenko.gradle-git-properties") version gitPropVersion
    }

    extra["kotlin.version"] = "1.7.2"
}

