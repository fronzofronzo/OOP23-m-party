plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.46.0"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.4")

    // Example library: Guava. Add what you need (and remove Guuava if you don't use it)
    // implementation("com.google.guava:guava:28.1-jre")
    // JavaFX: comment out if you do not need them
    val javaFxVersion = 15
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.spotbugs:spotbugs-annotations:4.7.3")

    val jUnitVersion = "5.10.2"
    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testImplementation("com.github.spotbugs:spotbugs-annotations:4.7.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

    val testFxVersion = "4.0.16-alpha"
    testImplementation("org.testfx:testfx-core:$testFxVersion")
    testImplementation("org.testfx:testfx-junit5:$testFxVersion")

    // SLF4J API
    implementation ("org.slf4j:slf4j-api:2.0.7")
    // Logback Classic
    implementation ("ch.qos.logback:logback-classic:1.4.11")
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
}

val main: String by project

application {
    // Define the main class for the application
    mainClass.set(main)
}
