plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
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

	//WEBFLUX DEPENDENCIES
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	//LOMBOK DEPENDENCIES
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//JUNIT TESTING DEPENDENCIES
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
