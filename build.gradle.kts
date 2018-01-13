allprojects {
    group = "io.github.ehedbor"
    version = "1.0-SNAPSHOT"
}

plugins {
    application
    kotlin("jvm") version "1.2.10"
}

application {
    mainClassName = "io.github.ehedbor.diskordlin.MainKt"
}

repositories {
    jcenter()
}

dependencies {
    // standard library. required to run kotlin code
    compile(kotlin("stdlib"))
    // used to parse json into objects
    compile("com.beust:klaxon:2.0.6")

    // testing framework
    testCompile("io.kotlintest:kotlintest:2.0.0")
}

