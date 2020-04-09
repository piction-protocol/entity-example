import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("plugin.jpa") version "1.3.61"
	`maven-publish`
}

group = "network.piction.example"
version = "1.0.0"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["kotlin"])
		}
	}

	repositories {
		maven {
			name = "github"
			url = uri("https://maven.pkg.github.com/piction-protocol/entity-example")
			credentials {
				username = System.getenv("PUBLISH_USERNAME")
				password = System.getenv("PUBLISH_TOKEN")
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
