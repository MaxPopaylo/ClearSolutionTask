import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    application
}

group = "app"

object Versions {
    const val MAPSTRUCT_VER = "1.5.5.Final"
    const val LOMBOK_MAPSTRUCT_BINDING_VER = "0.2.0"
}

repositories {
    mavenCentral()
}

dependencies {

    //MODULES
    implementation(project(path = ":domain"))
    implementation(project(path = ":application"))

    //MAPSTRUCT DEPENDENCIES
    implementation("org.mapstruct:mapstruct:${Versions.MAPSTRUCT_VER}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.MAPSTRUCT_VER}")
    implementation("org.projectlombok:lombok-mapstruct-binding:${Versions.LOMBOK_MAPSTRUCT_BINDING_VER}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${Versions.LOMBOK_MAPSTRUCT_BINDING_VER}")

    //VALIDATION DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //REST DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-web")

    //DATABASE DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    //LOMBOK DEPENDENCIES
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //WEBFLUX DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    //JUNIT TESTING DEPENDENCIES
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.withType<BootJar> {
    mainClass = "app.SolutionApplication"
}
tasks.test {
    useJUnitPlatform()
}

