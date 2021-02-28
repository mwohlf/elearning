plugins {
    idea
    base
}

allprojects {
    val projectGroup: String by project
    val projectVersion: String by project
    group = projectGroup
    version = projectVersion

    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    // see: https://docs.gradle.org/current/userguide/publishing_maven.html
    apply(plugin = "maven-publish")
}

tasks.register<DefaultTask>("info") {
    println("-- some commandlines for this project --")
    println("   * gradle build: create the artifacts")
    println("   * gradle publishToMavenLocal: publish artifacts")
    println("   * gradle bootRun: start the springboot application")
}