import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springframework.boot") version "3.3.0-SNAPSHOT"
}

group = "net.projecttl"
version = "1.0.0-SNAPSHOT"

val exposed_version: String by project

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
	maven("https://repo.spring.io/snapshot")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
//	implementation("org.apache.tomcat:tomcat-jsp-api:11.0.0-M17") // Apache Tomcat API
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
	implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposed_version")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	withType<Javadoc> {
		options.encoding = "UTF-8"
	}

	shadowJar {
		archiveBaseName.set(project.name)
		archiveClassifier.set("")
		archiveVersion.set("")
	}

	withType<Test> {
		useJUnitPlatform()
	}
}
