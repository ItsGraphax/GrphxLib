plugins {
    java
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("GrphxLib") {
            from(components["java"])
        }
    }
}

tasks {}
