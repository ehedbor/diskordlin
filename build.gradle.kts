import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

allprojects {
    group = "io.github.ehedbor"
    version = "0.0.1"
}

plugins {
    application
    kotlin("jvm") version "1.2.21"
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

tasks {
    "createProperties" {
        doLast {
            val file = File("$buildDir/resources/build-info.properties")
            file.createNewFile()
            file.printWriter().use { writer ->
                val props = Properties()
                with(props) {
                    this["group"] = project.group.toString()
                    this["name"] = project.name.toString()
                    this["version"] = project.version.toString()
                    this["url"] = "https://github.com/ehedbor/diskordlin"
                    store(writer, "Information for this Gradle build")
                }
            }
        }
    }
    withType<Jar> {
        manifest {
            attributes(mapOf(
                "Specification-Title" to project.name.toString(),
                "Specification-Version" to project.version.toString(),
                "Specification-Vendor" to "Evan Hedbor",
                "Implementation-Title" to "${project.group}.${project.name}",
                "Implementation-Version" to project.version.toString(),
                "Implementation-Vendor" to "Evan Hedbor"
            ))
        }
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}