plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "9.0.0"
}

group = "de.lunatig"
version = project.findProperty("version") ?: "dev"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = group.toString()
            artifactId = project.name
            version = version.toString()

            pom {
                name.set("Mini Message Parser")
                description.set("A simple message parser for Minecraft plugins.")
                url.set("https://github.com/LunatigTV/MiniMessageParser")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("lunatig")
                        name.set("LunatigTV")
                        url.set("https://github.com/LunatigTV/")
                    }
                }
                scm {
                    url.set("https://github.com/LunatigTV/MiniMessageParser")
                    connection.set("scm:git:git://github.com/LunatigTV/MiniMessageParser.git")
                    developerConnection.set("scm:git:ssh://git@github.com/LunatigTV/MiniMessageParser.git")
                }
            }
        }
    }
}