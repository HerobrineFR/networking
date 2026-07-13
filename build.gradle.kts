plugins {
    kotlin("jvm") version "2.3.10"
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
    id("com.vanniktech.maven.publish") version "0.30.0"
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

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("fr.herobrine", rootProject.name, rootProject.version.toString())

    pom {
        name.set("Networking")
        description.set("Networking API for herobrine.fr")
        inceptionYear.set("2026")
        url.set("https://github.com/HerobrineFR/networking")

        scm {
            connection.set("scm:git:git://://github.com")
            developerConnection.set("scm:git:ssh://://github.com")
            url.set("https://github.com/HerobrineFR/networking")
        }
    }
}