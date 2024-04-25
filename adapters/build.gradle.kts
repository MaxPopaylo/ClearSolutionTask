plugins {
    id("java")
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

}

tasks.test {
    useJUnitPlatform()
}