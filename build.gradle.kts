plugins {
    `java-library`
    `maven-publish`
}

group = "com.github.idan.koblik"
version = "0.1-SNAPSHOT"

allprojects {
    apply<JavaLibraryPlugin>()

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:${findProperty("junit.version")}"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

dependencies {
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}