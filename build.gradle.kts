import org.jetbrains.kotlin.gradle.dsl.Coroutines

allprojects {
    group = "io.github.ehedbor"
    version = "0.0.0"
}

plugins {
    application
    kotlin("jvm") version "1.2.21"
}

application {
    //mainClassName = "io.github.ehedbor.diskordlin.MainKt"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

repositories {
    jcenter()
}

dependencies {
    val kotlinxCoroutinesVersion = "0.21.1"
    val tyrusVersion = "1.13.1"
    val kotsonVersion = "2.5.0"
    val fuelVersion = "1.12.0"
    val slf4jVersion = "1.7.5"
    val kotlinLoggingVersion = "1.4.9"
    val kotlinTestVersion = "2.0.0"

    compile(kotlin("stdlib"))
    compile(kotlin("reflect"))
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    compile("org.glassfish.tyrus.bundles:tyrus-standalone-client-jdk:$tyrusVersion")
    compile("com.github.salomonbrys.kotson:kotson:$kotsonVersion")
    compile("com.github.kittinunf.fuel:fuel:$fuelVersion")
    compile("com.github.kittinunf.fuel:fuel-gson:$fuelVersion")
    //compile("org.slf4j:slf4j-api:$slf4jVersion")
    //compile("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

    testCompile("io.kotlintest:kotlintest:$kotlinTestVersion")
    //testCompile("org.slf4j:slf4j-simple:$slf4jVersion")
}

