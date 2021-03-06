import org.springframework.boot.gradle.tasks.bundling.BootJar;

plugins {
    id("java")
    id("org.springframework.boot").version("2.4.3")
    id("io.spring.dependency-management").version("1.0.11.RELEASE")
   // id("docker")
   // id("com.palantir.git-version")
    // openjdk:15-jdk-buster-node-browsers
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

dependencies {
    // to monitor an interact with the backend application
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    // implementation("org.springframework.boot:spring-boot-starter-freemarker")
    // implementation("org.springframework.boot:spring-boot-starter-mail")
    // implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    // implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    // fetch the webjar from the local maven repo, we need to make sure there is a recent version
    implementation("net.wohlfart.next:frontend:0.0.1-SNAPSHOT")
    // implementation(project(":frontend"))
    // implementation("org.webjars:webjars-locator-core")


    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    // ---- testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
}

// https://docs.gradle.org/current/userguide/publishing_maven.html
publishing {
    publications {
        // see: https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#publishing-your-application
        create<MavenPublication>("backend") { // the name here is used to create dynamic taskNames
            artifact(tasks.getByName("bootJar"))
        }
    }
}

// see: https://github.com/gradle/gradle/issues/11862
// tasks.withType<GenerateModuleMetadata> {
//    enabled = false
// }

// see: https://spring.io/blog/2020/04/15/announcing-the-spring-authorization-server

tasks.named("compileJava") {
    // we need the frontend jar in the maven repo
    dependsOn(":frontend:publishToMavenLocal") //
}

tasks.named<BootJar>("bootJar") {
    dependsOn("compileJava") //
    archiveFileName.set("fat.jar")
}

tasks.create<Delete>("mrproper") {
    dependsOn("clean")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        // events(PASSED, SKIPPED, FAILED)
    }
}

// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Exec.html
// https://stackoverflow.com/questions/41984399/denied-requested-access-to-the-resource-is-denied-docker
tasks.create<Exec>("buildImage") {
    dependsOn("bootJar")
    executable("docker")
    args("build", "-t", "elearning:latest", ".")
}

tasks.create<Exec>("tagImage") {
    dependsOn("buildImage")
    executable("docker")
    args("tag", "elearning:latest", "reg4sono.azurecr.io/reg4sono:elearning-latest")
}

// see: https://docs.travis-ci.com/user/encryption-keys/
tasks.create<Exec>("repoLogin") {
    executable("docker")
    args("login",
        "-u=" + System.getenv("REGISTRY_USER"),
        "-p="  + System.getenv("REGISTRY_PASSWD"),
        System.getenv("REGISTRY_SERVER"))
}

tasks.create<Exec>("pushImage") {
    dependsOn("tagImage", "repoLogin")
    executable("docker")
    args("push", "reg4sono.azurecr.io/reg4sono:elearning-latest")
}



// push to...
//   Registry name
//
//  docker tag elearning:latest mwohlf/repo:elearning-latest
//  docker login -u="mwohlf" -p="2Eo5XfCiXJBpF23o"
//  docker push mwohlf/repo:elearning-latest
//
//  docker tag local-image:tagname new-repo:tagname
//   docker push new-repo:tagname
