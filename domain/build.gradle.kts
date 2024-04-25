
plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}


group = "app"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    //LOMBOK DEPENDENCIES
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}