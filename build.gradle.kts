
plugins {
    id("java")

}

group = "org.joshuamatos"
version = "car-inventory"

repositories {
    mavenCentral()
}
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("com.formdev:flatlaf:3.0")
    implementation("com.formdev:flatlaf-intellij-themes:3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.joshuamatos.Main"
        attributes["Class-Path"] = configurations.runtimeClasspath.get().asPath
    }
}
