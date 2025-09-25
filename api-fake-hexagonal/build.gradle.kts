plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.arielZarate.api-fake"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(20)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql")
    //runtimeOnly("mysql:mysql-connector-java:8.0.33")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Kotest framework
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.8.1")

    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.1")

    testImplementation("io.mockk:mockk:1.13.13")
    // Instancio for generating test data
    testImplementation("org.instancio:instancio-core:5.2.1")
    testImplementation("org.instancio:instancio-junit:5.2.1")
    // JUnit integration (Kotest usa JUnit como base)
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")

    // Optional: Mockito (si decides usarlo también)
    testImplementation("org.mockito:mockito-core:5.14.2") //

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        //exceptionFormat = "full"
    }
}





