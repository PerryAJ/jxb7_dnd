import org.gradle.internal.impldep.aQute.bnd.build.Run

plugins {
    base
}

allprojects {
    group = "io.ia.example.jxbrowser7test"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
    }

}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}

