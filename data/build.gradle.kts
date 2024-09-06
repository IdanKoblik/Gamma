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

    // MongoDB asynchronous driver
    implementation("org.mongodb:mongodb-driver-reactivestreams:${findProperty("mongo.driver.version")}")
}