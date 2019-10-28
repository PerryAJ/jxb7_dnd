plugins {
    kotlin("jvm") version("1.3.21")
    application
}

repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
}


group = "es.perryjon.purdom"
version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "es.perryjon.purdom.ApplicationKt"
}

dependencies {
    // kotlin
    implementation(kotlin("stdlib"))

    // web framework
    implementation("io.javalin:javalin:3.3.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.jetbrains.pty4j:pty4j:0.8.6")

    // logging
    testCompile("org.slf4j:slf4j-api:1.7.25")
    compile("ch.qos.logback:logback-core:1.2.3")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testCompile("junit:junit:4.11")
}
