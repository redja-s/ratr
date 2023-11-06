plugins {
	java
	war
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	id("io.freefair.lombok") version "8.1.0"
}

group = "com.js"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(files("/home/jared/Documents/code/ratr/shared-lib/lib/build/libs/lib-plain.jar"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains:annotations:24.0.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")

	implementation("org.apache.logging.log4j:log4j-core:2.21.1")
	implementation("org.apache.logging.log4j:log4j-api:2.21.1")
	implementation("org.slf4j:slf4j-api:2.0.9")
	testImplementation("org.mockito:mockito-core:5.7.0")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
