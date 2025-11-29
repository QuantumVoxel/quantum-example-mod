import com.smushytaco.lwjgl_gradle.Module

plugins {
//	id 'fabric-loom' version '1.10-SNAPSHOT' // TODO Fork Fabric Loom for Quantum Voxel instead of Minecraft
    id("maven-publish")
    id("java")
    id("java-library")
    id("com.smushytaco.lwjgl3") version "1.0.0"
}

version = property("mod_version").toString()
group = property("maven_group").toString()

base {
    archivesName.set(property("archives_base_name").toString())
}

lwjgl {
    version = "3.4.0-SNAPSHOT"
    usePredefinedPlatforms = true
    api(Module.OPENGL, Module.OPENAL, Module.STB)
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    mavenCentral()

    maven("https://maven.ultreon.dev/releases")
    maven("https://maven.ultreon.dev/snapshots")
    maven("https://maven.fabricmc.net/")
    maven("https://jitpack.io")

    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/releases/")
}

dependencies {
    // To change the versions see the gradle.properties file
    api("dev.ultreon.qvoxel:client:0.1.0-alpha.2025.11.29")
    api("dev.ultreon.qvoxel:server:0.1.0-alpha.2025.11.29")

    runtimeOnly("dev.ultreon.qvoxel:gameprovider:0.1.0-alpha.2025.11.29")
}

tasks {
    processResources {
        inputs.property("version", project.version.toString())

        filesMatching("fabric.mod.json") {
            expand(inputs.properties)
        }
    }

    withType<ProcessResources>() {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<JavaCompile>().configureEach {
        options.release = 25
    }
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25

    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.register<JavaExec>("runClient") {
    dependsOn("classes")
    mainClass = "net.fabricmc.loader.impl.launch.knot.KnotClient"
    classpath = sourceSets.main.get().runtimeClasspath + sourceSets.main.get().output

    if (System.getProperty("os.name").contains("Mac")) {
        jvmArgs("-XstartOnFirstThread", "-Dfabric.development=true")
    }

    workingDir = file("run")
}

tasks.register<JavaExec>("runServer") {
    dependsOn("classes")
    mainClass = "net.fabricmc.loader.impl.launch.knot.KnotServer"
    classpath = sourceSets.main.get().runtimeClasspath + sourceSets.main.get().output

    workingDir = file("run")
}

tasks.jar {
    inputs.property("archivesName", project.base.archivesName)

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from("LICENSE") {
        rename { "${it}_${inputs.properties["archivesName"]}" }
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.property("archives_base_name").toString()
            from(components.named("java").get())
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}

mkdir("run")
