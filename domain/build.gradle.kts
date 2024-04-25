plugins {
    id("java")
}

group = "app"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    //WEBFLUX DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}