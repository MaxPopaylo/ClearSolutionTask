plugins {
    id("java")
}

group = "app"
version = "0.0.1-SNAPSHOT"

object Versions {
    const val lang3 = "3.14.0"
}

repositories {
    mavenCentral()
}

dependencies {

    //OTHER DEPENDENCIES
    implementation("org.apache.commons:commons-lang3:${Versions.lang3}")
}

tasks.test {
    useJUnitPlatform()
}