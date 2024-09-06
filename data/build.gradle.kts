plugins {
    id("java")
}

group = parent!!.group
version = parent!!.version

repositories {
    mavenCentral()
}

dependencies {
    // MongoDB synchronous driver
    implementation("org.mongodb:mongodb-driver-sync:${findProperty("mongo.driver.version")}")
    
    implementation("com.fasterxml.jackson.core:jackson-databind:${findProperty("jackson.version")}")
}