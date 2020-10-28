plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

group = "org.esia.specialinviter"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.slf4j", "slf4j-api", "1.7.25")
    implementation("org.apache.logging.log4j", "log4j-api", "2.13.3")
    implementation("org.apache.logging.log4j", "log4j-core", "2.13.3")
    testImplementation("junit", "junit", "4.12")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("org.assertj:assertj-core:3.15.0")
}
