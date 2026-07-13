plugins {
    kotlin("jvm") version "2.3.10"
    id("maven-publish")
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
}

dependencies {
    testImplementation(kotlin("test"))
    minecraft("com.mojang:minecraft:26.1.2")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}