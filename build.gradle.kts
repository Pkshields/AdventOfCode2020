import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "dev.paulshields.adventofcode2020"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
