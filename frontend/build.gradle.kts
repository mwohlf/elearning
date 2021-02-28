import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("java")  // for jar, includes the build task
    // see: https://github.com/node-gradle/gradle-node-plugin
    // https://github.com/node-gradle/gradle-node-plugin/blob/master/docs/usage.md
    id("com.github.node-gradle.node").version("3.0.1")
    // see: https://github.com/coditory/gradle-webjar-plugin
    // id("com.coditory.webjar").version("1.0.3")
}

// see: https://daggerok.github.io/spring-boot-gradle-kotlin-dsl-example/
// https://github.com/kucharzyk/spring-kotlin-angular4/blob/master/shardis-frontend/build.gradle.kts
// https://github.com/node-gradle/gradle-node-plugin/blob/master/build.gradle.kts
// https://dev.to/darkes/using-gradle-s-kotlin-dsl-to-bundle-react-with-a-spring-boot-web-application-355k

node {
    download.set(true)
    distBaseUrl.set("https://nodejs.org/dist")

    val projectNodeVersion: String by project
    version.set(projectNodeVersion)

    val projectNpmVersion: String by project
    npmVersion.set(projectNpmVersion)

    val projectYarnVersion: String by project
    yarnVersion.set(projectYarnVersion)
}

tasks.register<YarnTask>("buildFrontend") {
    dependsOn("yarn_install") //

    description = "Builds the frontend into the dist directory"
    // outputs.dir("src/main/resources/static")
    // workingDir = file("${project.projectDir}/src/main/webapp")
    args.set(listOf("run", "build"))
}

// tasks.register<DefaultTask>("build") {
tasks.named("processResources") {
    dependsOn("buildFrontend") //
}

// see: https://fbflex.wordpress.com/2014/03/14/building-web-content-jars-for-spring-boot-with-gradle/
tasks.register<Jar>("webjar") {
    // dependsOn("jar") // done with finalizedBy in jar
    from(fileTree("build/dist")) {
        into("META-INF/resources")
    }
}

tasks.named("jar") {
    dependsOn("buildFrontend") //
    finalizedBy("webjar") //
}

// tasks.register<DefaultTask>("build") {
tasks.create<Delete>("mrproper") {
    dependsOn("clean")
    delete = setOf (
        ".node", "node_modules"
    )
}

publishing {
    publications {
        // see: https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#publishing-your-application
        create<MavenPublication>("frontend") {
            artifact(tasks.getByName("webjar"))
        }
    }
}

/*
tasks.getByName("clean").doLast {
    delete("node_modules")
    delete("coverage")
    delete("documentation")
    delete("dist")
}


yarn install
yarn start


*/

/*
tasks.create("start")
tasks["start"].dependsOn("npm_start")
tasks["npm_start"].dependsOn("npm_i")
tasks["build"].dependsOn("npm_run_build")
tasks["npm_run_build"].dependsOn("npm_install")
*/
// 3
// jar.dependsOn 'npm_run_build'

// 4
//jar {
//    from 'dist/todo-ui' into 'static'
//}
