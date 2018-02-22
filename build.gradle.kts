import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.internal.HasConvention
import org.gradle.internal.impldep.com.amazonaws.services.s3.model.SSEAlgorithm
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

buildscript {
    extra["kotlinVersion"] = "1.2.21"
    extra["kotlinxCoroutinesVersion"] = "0.21.1"
    extra["kotlinSerializationVersion"] = "0.4.1"

    extra["tyrusVersion"] = "1.13.1"
    extra["kotsonVersion"] = "2.5.0"
    extra["fuelVersion"] = "1.12.0"
    extra["slf4jVersion"] = "1.7.5"
    extra["kotlinLoggingVersion"] = "1.4.9"

    extra["kotlinTestVersion"] = "2.0.0"
    extra["log4jVersion"] = "2.7"

    repositories {
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx")
    }
    dependencies {
        classpath(kotlin("gradle-plugin", extra["kotlinVersion"].toString()))
        classpath("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${extra["kotlinSerializationVersion"]}")
    }
}

allprojects {
    group = "io.github.ehedbor"
    version = "0.0.1"
}

plugins {
    base
    kotlin("jvm") version "1.2.21"
}

apply {
    plugin("kotlin")
    plugin("kotlinx-serialization")
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    compile(kotlin("stdlib", extra["kotlinVersion"].toString()))
    compile(kotlin("reflect", extra["kotlinVersion"].toString()))
    compile(kotlinx("coroutines-core", extra["kotlinxCoroutinesVersion"].toString()))
    compile(kotlinx("serialization-runtime", extra["kotlinSerializationVersion"].toString()))
    compile("org.glassfish.tyrus.bundles:tyrus-standalone-client-jdk:${extra["tyrusVersion"]}")
    compile("com.github.salomonbrys.kotson:kotson:${extra["kotsonVersion"]}")
    compile("com.github.kittinunf.fuel:fuel:${extra["fuelVersion"]}")
    compile("com.github.kittinunf.fuel:fuel-gson:${extra["fuelVersion"]}")
    compile("org.slf4j:slf4j-api:${extra["slf4jVersion"]}")
    compile("io.github.microutils:kotlin-logging:${extra["kotlinLoggingVersion"]}")

    testCompile("io.kotlintest:kotlintest:${extra["kotlinTestVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-api:${extra["log4jVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-core:${extra["log4jVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-slf4j-impl:${extra["log4jVersion"]}")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    val processResources by tasks
    val createProperties = task("createProperties") {
        dependsOn(processResources)
        doLast {
            val file = File("$buildDir/resources/main/build-info.properties")
            file.parentFile.mkdirs()
            file.createNewFile()
            file.printWriter().use { writer ->
                val p = Properties()
                p["name"] = project.displayName
                p["version"] = project.version.toString()
                p["url"] = "https://github.com/ehedbor/diskordlin"
                p.store(writer, null)
            }
        }
    }
    val classes by tasks
    classes.dependsOn(createProperties)
}

fun DependencyHandler.kotlinx(module: String, version: String? = null)
    = "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"