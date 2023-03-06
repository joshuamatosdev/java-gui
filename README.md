
# Java GUI
ReadMe. for Java GUI Course


Course Available at: https://www.youtube.com/watch?v=cNx7AqRoPSM

Description: In this video, we will learn how to build a Car Inventory GUI using Java Swing. We will start by creating a JFrame with various JComponents like JTextFields, JPanels, JLabels, and JButtons. Then we will make an ArrayList of Car objects and implement multiple functionalities like adding a new car to the inventory, deleting a car, displaying the list of cars, clearing the text fields, and saving the inventory to a CSV file. 

Finally, we will use GridLayout, BoxLayout, and BorderLayout to arrange the components and set borders to make the GUI look more appealing. This tutorial is suitable for beginners who understand Java programming language and want to learn how to create a GUI using Java Swing.


## Instructions:
In IntelliJ, create a New Project, give your project a name and select Java as it's language. 
Select Gradle as the build system and use Java 17 or higher as your JDK. Use the Kotln version of the Gradle DSL. Then click Advance settings and change your GroupId to whatever you like. 

Paste the code below inside of your build.gradle.kts, and replace the org.example GroupId with yours or leave it as is, if you didn't change yours.
  
## File: build.gradle.kts


```kotlin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

//in the video I used org.joshuamatos, replace with your groupID
group = "org.example"
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

//in the video I used org.joshuamatos, replace with your groupID
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
        attributes["Class-Path"] = configurations.runtimeClasspath.get().asPath
    }
}

//in the video I used org.joshuamatos, replace with your groupID
tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}
```

