buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.flywaydb:flyway-database-postgresql:10.7.2")
	}
}

plugins {
	java
	war
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	id("io.freefair.lombok") version "8.1.0"
	id("org.flywaydb.flyway") version "10.7.2"
}

group = "com.js"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	implementation("com.ratr:shared-lib:1.1")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testImplementation("org.mockito:mockito-core:5.7.0")
	testImplementation("junit:junit:4.13.1")

	// Logging
	implementation("org.slf4j:slf4j-api:2.0.7")
	implementation("org.apache.logging.log4j:log4j-api:2.21.0")
	implementation("org.apache.logging.log4j:log4j-core:2.21.0")
	implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.21.0")

	// MapStruct
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")

	// Flyway
	implementation("org.flywaydb:flyway-core:10.7.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//https://documentation.red-gate.com/fd/parameters-224919673.html
flyway {
	url = "jdbc:postgresql://localhost:5432/postgres"
	user = "postgres"
	password = "password"
	baselineOnMigrate = true
}
