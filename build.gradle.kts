/*
 * MIT License
 *
 * Copyright (c) 2017-2018 Evan Hedbor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.internal.HasConvention
import org.gradle.internal.impldep.com.amazonaws.services.s3.model.SSEAlgorithm
import org.gradle.internal.impldep.org.joda.time.format.DateTimeFormat
import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.util.Date
import java.text.SimpleDateFormat

buildscript {
    extra["kotlinVersion"] = "1.2.31"
    extra["kotlinxCoroutinesVersion"] = "0.21.1"
    extra["kotlinSerializationVersion"] = "0.4.2"
    extra["dokkaVersion"] = "0.9.16"

    extra["tyrusVersion"] = "1.13.1"
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
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${extra["dokkaVersion"]}")
        classpath("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${extra["kotlinSerializationVersion"]}")
    }
}

allprojects {
    group = "io.github.ehedbor"
    version = "0.0.2"
}

plugins {
    base
    kotlin("jvm") version "1.2.31"
    `maven-publish`
    id("com.jfrog.bintray") version "1.7.3"
}

apply {
    plugin("kotlin")
    plugin("org.jetbrains.dokka")
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
    compile(kotlin("stdlib-jdk8", extra["kotlinVersion"].toString()))
    compile(kotlin("reflect", extra["kotlinVersion"].toString()))
    compile(kotlinx("coroutines-core", extra["kotlinxCoroutinesVersion"].toString()))
    compile(kotlinx("serialization-runtime", extra["kotlinSerializationVersion"].toString()))
    compile("com.beust:klaxon:2.1.14")
    compile("org.glassfish.tyrus.bundles:tyrus-standalone-client-jdk:${extra["tyrusVersion"]}")
    compile("com.github.kittinunf.fuel:fuel:${extra["fuelVersion"]}")
    compile("org.slf4j:slf4j-api:${extra["slf4jVersion"]}")
    compile("io.github.microutils:kotlin-logging:${extra["kotlinLoggingVersion"]}")


    testCompile("io.kotlintest:kotlintest:${extra["kotlinTestVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-api:${extra["log4jVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-core:${extra["log4jVersion"]}")
    testCompile("org.apache.logging.log4j:log4j-slf4j-impl:${extra["log4jVersion"]}")
}

val processResources by tasks
val createProperties by tasks.creating {
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

val javadoc by tasks.getting(Javadoc::class)

val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "gfm"
    outputDirectory = javadoc.destinationDir.absolutePath
    inputs.dir("src/main/kotlin")
}

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(classes)
    classifier = "sources"
    from(java.sourceSets["main"].allSource)
}

val dokkaJar by tasks.creating(Jar::class) {
    dependsOn(dokka)
    from(javadoc.destinationDir)
    classifier = "invalid"
}

publishing {
    (publications) {
        "BintrayRelease"(MavenPublication::class) {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(sourcesJar)
            artifact(dokkaJar)
        }
    }
}

bintray {
    user = getProjectProperty("bintray.user")
    key = getProjectProperty("bintray.key")
    setPublications("BintrayRelease")
    pkg.apply {
        repo = "maven"
        name = "diskordlin"
        setLicenses("MIT")
        vcsUrl = "https://github.com/ehedbor/diskordlin.git"
        publish = true
        version.apply {
            name = "${project.version}"
            val dtf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
            released = dtf.format(Date())
        }
    }
}

fun DependencyHandler.kotlinx(module: String, version: String? = null) = "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" }
    ?: ""}"

fun getProjectProperty(name: String): String = if (hasProperty(name)) properties[name] as String else ""
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}