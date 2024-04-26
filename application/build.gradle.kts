
plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "app"
version = "0.0.1-SNAPSHOT"

object Versions {
    const val LANG3_VER = "3.14.0"
}

repositories {
    mavenCentral()
}

dependencies {

    //MODULES
    implementation(project(path = ":domain"))

    //WEBFLUX DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    //LOMBOK DEPENDENCIES
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //OTHER DEPENDENCIES
    implementation("org.apache.commons:commons-lang3:${Versions.LANG3_VER}")

    //JUNIT TESTING DEPENDENCIES
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}