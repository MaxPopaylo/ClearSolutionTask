
plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	application
}

application {
	mainClass = "solution.SolutionApplication"
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
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
