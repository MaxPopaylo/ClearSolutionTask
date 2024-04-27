import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "app"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {

	//MODULES
	implementation(project(path = ":adapters"))
	implementation(project(path = ":application"))
	implementation(project(path = ":domain"))

	//DEPLOY DEPENDENCIES
	implementation("org.springframework.boot:spring-boot-starter")

	//JUNIT TESTING DEPENDENCIES
	testImplementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:r2dbc")



}

tasks.register<Test>("integrationTests") {
	useJUnitPlatform {
		excludeTags("unit")
		includeTags("integration")
	}
	mustRunAfter("check")
}

tasks.register<Test>("unitTests") {
	useJUnitPlatform {
		includeTags("unit")
		excludeTags("integration")
	}
	mustRunAfter("check")
}

tasks.withType<BootJar> {
	mainClass = "app.SolutionApplication"
}

tasks.withType<Test> {
	useJUnitPlatform()
}
