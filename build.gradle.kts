import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
import org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "org.esia.specialinviter"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.logging.log4j", "log4j-api", "2.13.3")
    implementation("org.apache.logging.log4j", "log4j-core", "2.13.3")
    implementation("org.json", "json", "20200518")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.assertj:assertj-core:3.15.0")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "org.esia.specialinviter.AppKt"
    }
}

tasks.withType<Test> {
    this.testLogging {
        // set options for log level LIFECYCLE
        events = setOf(FAILED, PASSED, SKIPPED, STANDARD_OUT)
        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true

        // set options for log level DEBUG and INFO
        debug {
            events = setOf(STARTED, FAILED, PASSED, SKIPPED, STANDARD_ERROR, STANDARD_OUT)
            exceptionFormat = FULL
        }
        info.events = debug.events
        info.exceptionFormat = debug.exceptionFormat

        addTestListener(object : TestListener {
            override fun beforeTest(p0: TestDescriptor?) = Unit
            override fun beforeSuite(p0: TestDescriptor?) = Unit
            override fun afterTest(desc: TestDescriptor, result: TestResult) = Unit
            override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                printResults(desc, result)
            }
        })
    }
}

fun printResults(desc: TestDescriptor, result: TestResult) {
    if (desc.parent != null) {
        val output = result.run {
            "Results: $resultType (" +
                "$testCount tests, " +
                "$successfulTestCount successes, " +
                "$failedTestCount failures, " +
                "$skippedTestCount skipped" +
                ") ${desc.className ?: desc.parent}"
        }
        val testResultLine = "|  $output  |"
        val repeatLength = testResultLine.length
        val seperationLine = "-".repeat(repeatLength)
        println(seperationLine)
        println(testResultLine)
        println(seperationLine)
    }
}
