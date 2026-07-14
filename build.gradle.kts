plugins {
    `java-library`
    kotlin("jvm") version "2.3.10"
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
    id("com.vanniktech.maven.publish") version "0.37.0"
    id("org.jetbrains.dokka") version "2.1.0"
}

dependencies {
    testImplementation(kotlin("test"))
    minecraft("com.mojang:minecraft:26.1.2")
}

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates(rootProject.group.toString(), rootProject.name, rootProject.version.toString())

    pom {
        name.set("Networking")
        description.set("Networking API for herobrine.fr")
        inceptionYear.set("2026")
        url.set("https://github.com/HerobrineFR/networking")

        developers {
            developer {
                id.set("Yaazor")
                name.set("Yazor")
                url.set("https://github.com/Yaazor")
            }
        }

        licenses {
            license {
                name.set("The MIT License")
                url.set("https://opensource.org")
                distribution.set("repo")
            }
        }

        scm {
            url.set("https://github.com/HerobrineFR/networking/")
            connection.set("scm:git:git://github.com/HerobrineFR/networking.git")
            developerConnection.set("scm:git:ssh://git@github.com/HerobrineFR/networking.git")
        }

    }
}
